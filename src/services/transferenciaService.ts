import { prisma } from '../lib/prisma';
import { TransferenciaDTO, TransferenciaResponseDTO } from '../dtos/transferencia.dto';
import {
  PagadorLojistaException,
  SaldoInsuficienteException,
  UsuarioInexistenteException,
} from '../exceptions/AppError';
import { autorizar } from './autorizadorService';
import { notificar } from './notificacaoService';
import { Decimal } from '@prisma/client/runtime/library';

export async function fazerTransferencia(
  data: TransferenciaDTO,
  pagadorId: string
): Promise<TransferenciaResponseDTO> {
  const [pagador, recebedor] = await Promise.all([
    prisma.usuario.findUnique({ where: { id: pagadorId } }),
    prisma.usuario.findFirst({
      where: { OR: [{ documento: data.recebedor }, { email: data.recebedor }] },
    }),
  ]);

  if (!pagador || !recebedor) {
    throw new UsuarioInexistenteException();
  }

  if (pagador.tipoCliente === 'LOJISTA') {
    throw new PagadorLojistaException();
  }

  const quantia = new Decimal(data.quantia);

  if (pagador.saldo.lessThan(quantia)) {
    throw new SaldoInsuficienteException();
  }

  await autorizar();

  const transferencia = await prisma.$transaction(async (tx) => {
    await tx.usuario.update({
      where: { id: pagador.id },
      data: { saldo: { decrement: quantia } },
    });

    await tx.usuario.update({
      where: { id: recebedor.id },
      data: { saldo: { increment: quantia } },
    });

    return tx.transferencia.create({
      data: {
        pagadorId: pagador.id,
        recebedorId: recebedor.id,
        quantia,
        status: 'CONCLUIDA',
      },
    });
  });

  notificar();

  return {
    id: transferencia.id,
    nomePagador: pagador.nome,
    nomeRecebedor: recebedor.nome,
    quantia: transferencia.quantia.toString(),
    status: transferencia.status,
    horaTransacao: transferencia.horaTransacao,
  };
}
