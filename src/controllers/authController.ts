import { Request, Response, NextFunction } from 'express';
import { z } from 'zod';
import { login } from '../services/authService';

const LoginSchema = z.object({
  email: z.string().email(),
  senha: z.string().min(1),
});

export async function loginController(req: Request, res: Response, next: NextFunction) {
  try {
    const { email, senha } = LoginSchema.parse(req.body);
    const result = await login(email, senha);
    res.status(200).json(result);
  } catch (err) {
    next(err);
  }
}
