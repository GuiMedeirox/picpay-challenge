import jwt from 'jsonwebtoken';
import { TipoCliente } from '@prisma/client';

const SECRET = process.env.JWT_SECRET!;
const EXPIRES_IN = process.env.JWT_EXPIRES_IN ?? '1d';

export interface JwtPayload {
  sub: string;
  email: string;
  tipoCliente: TipoCliente;
}

export function signToken(payload: JwtPayload): string {
  return jwt.sign(payload, SECRET, { expiresIn: EXPIRES_IN } as jwt.SignOptions);
}

export function verifyToken(token: string): JwtPayload {
  return jwt.verify(token, SECRET) as JwtPayload;
}
