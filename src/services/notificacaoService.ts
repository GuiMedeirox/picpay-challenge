import axios from 'axios';

export async function notificar(): Promise<void> {
  try {
    await axios.post('https://util.devi.tools/api/v1/notify');
  } catch (err) {
    console.error('Deu pau na requisicao da notificacao:', err);
  }
}
