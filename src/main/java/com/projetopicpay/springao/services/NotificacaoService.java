package com.projetopicpay.springao.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class NotificacaoService {

    public void notificar(){
        try{
            RestClient.create()
                    .post()
                    .uri("https://util.devi.tools/api/v1/notify")
                    .retrieve()
                    .toBodilessEntity();
        } catch(Exception err){
            log.error("Deu pau aqui na requisicao da notificacao: " +err.getMessage());
        }
    }


}
