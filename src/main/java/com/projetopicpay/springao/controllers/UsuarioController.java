package com.projetopicpay.springao.controllers;

import com.projetopicpay.springao.dtos.UsuarioCadastroDTO;
import com.projetopicpay.springao.dtos.UsuarioResponseDTO;
import com.projetopicpay.springao.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario( @Valid @RequestBody UsuarioCadastroDTO us){
        UsuarioResponseDTO usuarioResponse =  usuarioService.criarUsuario(us);

        return new ResponseEntity<>(usuarioResponse,HttpStatus.CREATED);
    }

}
