package com.projetopicpay.springao.services;

import com.projetopicpay.springao.dtos.TransferenciaDTO;
import com.projetopicpay.springao.enums.TipoCliente;
import com.projetopicpay.springao.exception.PagadorLojista;
import com.projetopicpay.springao.exception.SaldoInsuficiente;
import com.projetopicpay.springao.exception.UsuarioInexistente;
import com.projetopicpay.springao.models.Usuario;
import com.projetopicpay.springao.repositories.TransferenciaRepository;
import com.projetopicpay.springao.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferenciaService
{

    private final UsuarioRepository usuarioRepository;
    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaDTO fazerTransferencia(TransferenciaDTO transf){

        Optional<Usuario> pagador = usuarioRepository.findByDocumentoOrEmail(transf.pagador(), transf.pagador());
        Optional<Usuario> recebedor = usuarioRepository.findByDocumentoOrEmail(transf.recebedor(), transf.recebedor());

        if(pagador.isEmpty() || recebedor.isEmpty()){
            throw new UsuarioInexistente("um dos usuarios informados sao inexistentes");
        }

        if(pagador.get().getTipoCliente() == TipoCliente.LOJISTA){
            throw new PagadorLojista("o pagador nao pode ser do tipo lojista");
        }

        if( pagador.get().getSaldo().compareTo(transf.quantia()) < 0 ){
            throw new SaldoInsuficiente("o saldo do pagador é insuficiente");
        }


    }


}
