package com.projetopicpay.springao.exception;

public class UsuarioExistenteException extends RuntimeException{

    public UsuarioExistenteException(String msg){
        super(msg);
    }

}
