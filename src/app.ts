import express from 'express';
import usuarioRoutes from './routes/usuarioRoutes';
import transferenciaRoutes from './routes/transferenciaRoutes';
import authRoutes from './routes/authRoutes';
import { errorHandler } from './middlewares/errorHandler';

const app = express();

app.use(express.json());

app.use('/api/auth', authRoutes);
app.use('/api/usuario', usuarioRoutes);
app.use('/api/transferencia', transferenciaRoutes);

app.use(errorHandler);

export default app;
