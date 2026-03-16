import { Request, Response, NextFunction } from 'express';
import { TransferenciaDTOSchema } from '../dtos/transferencia.dto';
import { fazerTransferencia } from '../services/transferenciaService';

export async function createTransferencia(req: Request, res: Response, next: NextFunction) {
  try {
    const body = TransferenciaDTOSchema.parse(req.body);
    const transferencia = await fazerTransferencia(body, req.user!.id);
    res.status(200).json(transferencia);
  } catch (err) {
    next(err);
  }
}
