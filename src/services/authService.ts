import bcrypt from 'bcryptjs';
import { prisma } from '../lib/prisma';
import { signToken } from '../lib/jwt';
import { AppError } from '../exceptions/AppError';

export async function login(email: string, senha: string): Promise<{ token: string }> {
  const usuario = await prisma.usuario.findUnique({ where: { email } });

  if (!usuario || !(await bcrypt.compare(senha, usuario.senha))) {
    throw new AppError('Credenciais inválidas', 401);
  }

  const token = signToken({ sub: usuario.id, email: usuario.email, tipoCliente: usuario.tipoCliente });

  return { token };
}
