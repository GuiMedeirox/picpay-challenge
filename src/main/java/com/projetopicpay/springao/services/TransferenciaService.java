package com.projetopicpay.springao.services;

import com.projetopicpay.springao.dtos.TransferenciaDTO;
import com.projetopicpay.springao.dtos.TransferenciaResponseDTO;
import com.projetopicpay.springao.enums.StatusTransferencia;
import com.projetopicpay.springao.enums.TipoCliente;
import com.projetopicpay.springao.exception.PagadorLojista;
import com.projetopicpay.springao.exception.PagamentoNaoAutorizado;
import com.projetopicpay.springao.exception.SaldoInsuficiente;
import com.projetopicpay.springao.exception.UsuarioInexistente;
import com.projetopicpay.springao.models.Transferencia;
import com.projetopicpay.springao.models.Usuario;
import com.projetopicpay.springao.repositories.TransferenciaRepository;
import com.projetopicpay.springao.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferenciaService
{

    private final UsuarioRepository usuarioRepository;
    private final TransferenciaRepository transferenciaRepository;
    private final AutorizadorService autorizadorService;
    private final NotificacaoService notificacaoService;
    @Transactional
    public TransferenciaResponseDTO fazerTransferencia(TransferenciaDTO transf){

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

        boolean status = autorizadorService.autorizar();

        recebedor.get().setSaldo(
                recebedor.get().getSaldo().add(transf.quantia())
        );

        pagador.get().setSaldo(
                pagador.get().getSaldo().subtract(transf.quantia())
        );

        usuarioRepository.save(pagador.get());
        usuarioRepository.save(recebedor.get());

        Transferencia transferencia = new Transferencia(pagador.get(), recebedor.get(), transf.quantia(), StatusTransferencia.CONCLUIDA);
        transferenciaRepository.save(transferencia);
        notificacaoService.notificar();

        return new TransferenciaResponseDTO(transferencia.getId(), pagador.get().getNome(), recebedor.get().getNome(), transf.quantia(), transferencia.getStatus(), transferencia.getHora_transacao());

    }


}
