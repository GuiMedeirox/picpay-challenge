import { Request, Response, NextFunction } from 'express';
import { ZodError } from 'zod';
import { AppError } from '../exceptions/AppError';

export function errorHandler(err: unknown, req: Request, res: Response, _next: NextFunction) {
  if (err instanceof ZodError) {
    res.status(400).json({
      message: 'Dados inválidos',
      errors: err.flatten().fieldErrors,
    });
    return;
  }

  if (err instanceof AppError) {
    res.status(err.statusCode).json({ message: err.message });
    return;
  }

  console.error(err);
  res.status(500).json({ message: 'Erro interno do servidor' });
}
