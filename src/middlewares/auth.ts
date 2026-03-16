import { Request, Response, NextFunction } from 'express';
import { verifyToken } from '../lib/jwt';
import { AppError } from '../exceptions/AppError';

export function authMiddleware(req: Request, _res: Response, next: NextFunction) {
  const authHeader = req.headers.authorization;

  if (!authHeader?.startsWith('Bearer ')) {
    return next(new AppError('Token não fornecido', 401));
  }

  const token = authHeader.slice(7);

  try {
    const payload = verifyToken(token);
    req.user = { id: payload.sub, email: payload.email, tipoCliente: payload.tipoCliente };
    next();
  } catch {
    next(new AppError('Token inválido ou expirado', 401));
  }
}
