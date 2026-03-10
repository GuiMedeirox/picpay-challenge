package com.projetopicpay.springao.exception;

public class UsuarioInexistente extends RuntimeException {
    public UsuarioInexistente(String message) {
        super(message);
    }
}
