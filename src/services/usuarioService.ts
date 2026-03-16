import bcrypt from 'bcryptjs';
import { prisma } from '../lib/prisma';
import { UsuarioCadastroDTO, UsuarioResponseDTO } from '../dtos/usuario.dto';
import { UsuarioExistenteException } from '../exceptions/AppError';

export async function criarUsuario(data: UsuarioCadastroDTO): Promise<UsuarioResponseDTO> {
  const existente = await prisma.usuario.findFirst({
    where: {
      OR: [{ documento: data.documento }, { email: data.email }],
    },
  });

  if (existente) {
    throw new UsuarioExistenteException();
  }

  const senhaHash = await bcrypt.hash(data.senha, 10);

  const usuario = await prisma.usuario.create({
    data: {
      nome: data.nome,
      email: data.email,
      senha: senhaHash,
      documento: data.documento,
      tipoCliente: data.tipoCliente,
    },
  });

  return {
    nome: usuario.nome,
    email: usuario.email,
    documento: usuario.documento,
    saldo: usuario.saldo.toString(),
  };
}
