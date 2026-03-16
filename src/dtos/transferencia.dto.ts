import { z } from 'zod';

export const TransferenciaDTOSchema = z.object({
  recebedor: z.string().min(1),
  quantia: z.number().positive(),
});

export type TransferenciaDTO = z.infer<typeof TransferenciaDTOSchema>;

export type TransferenciaResponseDTO = {
  id: string;
  nomePagador: string;
  nomeRecebedor: string;
  quantia: string;
  status: string;
  horaTransacao: Date;
};
