package com.projetopicpay.springao.exception;

import com.projetopicpay.springao.dtos.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<ErroResponse> capturarExceptionUsuarioExistente(UsuarioExistenteException ex) {
        ErroResponse err = new ErroResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> capturarExceptionCamposFaltando(MethodArgumentNotValidException ex){
        ErroResponse err = new ErroResponse(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

}
