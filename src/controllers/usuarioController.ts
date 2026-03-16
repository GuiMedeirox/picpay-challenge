import { Request, Response, NextFunction } from 'express';
import { UsuarioCadastroDTOSchema } from '../dtos/usuario.dto';
import { criarUsuario } from '../services/usuarioService';

export async function createUsuario(req: Request, res: Response, next: NextFunction) {
  try {
    const body = UsuarioCadastroDTOSchema.parse(req.body);
    const usuario = await criarUsuario(body);
    res.status(201).json(usuario);
  } catch (err) {
    next(err);
  }
}
