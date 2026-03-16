export class AppError extends Error {
  constructor(
    public readonly message: string,
    public readonly statusCode: number = 400
  ) {
    super(message);
    this.name = this.constructor.name;
  }
}

export class UsuarioExistenteException extends AppError {
  constructor(message = 'usuario ja existente') {
    super(message, 409);
  }
}

export class UsuarioInexistenteException extends AppError {
  constructor(message = 'um dos usuarios informados sao inexistentes') {
    super(message, 404);
  }
}

export class PagadorLojistaException extends AppError {
  constructor(message = 'o pagador nao pode ser do tipo lojista') {
    super(message, 422);
  }
}

export class SaldoInsuficienteException extends AppError {
  constructor(message = 'o saldo do pagador é insuficiente') {
    super(message, 422);
  }
}

export class PagamentoNaoAutorizadoException extends AppError {
  constructor(message = 'servico externo de pagamento negou a transacao') {
    super(message, 422);
  }
}
