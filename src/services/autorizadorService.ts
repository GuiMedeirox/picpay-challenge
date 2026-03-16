import axios from 'axios';
import { PagamentoNaoAutorizadoException } from '../exceptions/AppError';

interface AutorizadorResponse {
  status: string;
  data: {
    authorization: boolean;
  };
}

export async function autorizar(): Promise<void> {
  const { data } = await axios.get<AutorizadorResponse>(
    'https://util.devi.tools/api/v2/authorize'
  );

  if (!data.data.authorization) {
    throw new PagamentoNaoAutorizadoException();
  }
}
