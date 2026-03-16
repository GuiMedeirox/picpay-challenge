import { z } from 'zod';

export const UsuarioCadastroDTOSchema = z.object({
  nome: z.string().min(1),
  senha: z.string().min(1),
  email: z.string().email(),
  documento: z.string().min(1).max(14),
  tipoCliente: z.enum(['CLIENTE', 'LOJISTA']),
});

export type UsuarioCadastroDTO = z.infer<typeof UsuarioCadastroDTOSchema>;

export type UsuarioResponseDTO = {
  nome: string;
  email: string;
  documento: string;
  saldo: string;
};
