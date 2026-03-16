import { Router } from 'express';
import { createTransferencia } from '../controllers/transferenciaController';
import { authMiddleware } from '../middlewares/auth';

const router = Router();

router.post('/', authMiddleware, createTransferencia);

export default router;
