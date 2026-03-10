package com.projetopicpay.springao.controllers;

import com.projetopicpay.springao.dtos.TransferenciaDTO;
import com.projetopicpay.springao.dtos.TransferenciaResponseDTO;
import com.projetopicpay.springao.services.TransferenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transferencia")
@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<TransferenciaResponseDTO> fazerTransferencia(@Valid  @RequestBody TransferenciaDTO transferenciaDTO){

        TransferenciaResponseDTO transf = transferenciaService.fazerTransferencia(transferenciaDTO);

        return new ResponseEntity<>(transf, HttpStatus.OK);
    }


}
