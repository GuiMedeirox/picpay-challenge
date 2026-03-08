package com.projetopicpay.springao.services;

import com.projetopicpay.springao.dtos.ErroResponse;
import com.projetopicpay.springao.dtos.UsuarioCadastroDTO;
import com.projetopicpay.springao.dtos.UsuarioResponseDTO;
import com.projetopicpay.springao.exception.UsuarioExistenteException;
import com.projetopicpay.springao.models.Usuario;
import com.projetopicpay.springao.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioResponseDTO criarUsuario(UsuarioCadastroDTO user){

        Optional<Usuario> usuario = repository.findByDocumentoOrEmail(user.documento(), user.email());

        if(usuario.isPresent()){
            throw new UsuarioExistenteException("usuario ja existente");
        }

        Usuario u = new Usuario();
        u.setDocumento(user.documento());
        u.setNome(user.nome());
        u.setSenha(user.senha());
        u.setEmail(user.email());
        u.setTipoCliente(user.tipoCliente());


        repository.save(u);


        return new UsuarioResponseDTO(u.getNome(), u.getEmail(), u.getDocumento(), u.getSaldo());
    }

}
