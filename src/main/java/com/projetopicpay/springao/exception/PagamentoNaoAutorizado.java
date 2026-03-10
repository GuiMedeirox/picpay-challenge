package com.projetopicpay.springao.exception;

public class PagamentoNaoAutorizado extends RuntimeException {
    public PagamentoNaoAutorizado(String message) {
        super(message);
    }
}
