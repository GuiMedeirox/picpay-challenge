package com.projetopicpay.springao.services;

import com.projetopicpay.springao.dtos.AutorizadorResponse;
import com.projetopicpay.springao.exception.PagamentoNaoAutorizado;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AutorizadorService {

    public Boolean autorizar(){
        AutorizadorResponse res = RestClient.create()
                .get()
                .uri("https://util.devi.tools/api/v2/authorize")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new PagamentoNaoAutorizado("Servico externo de pagamento negou a transacao");
                })
                .body(AutorizadorResponse.class);

        return res != null && res.data().authorization();
    }

}
