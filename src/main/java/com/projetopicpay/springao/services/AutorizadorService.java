package com.projetopicpay.springao.services;

import com.projetopicpay.springao.dtos.AutorizadorResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AutorizadorService {

    public Boolean autorizar(){
        AutorizadorResponse res = RestClient.create()
                .get()
                .uri("https://util.devi.tools/api/v2/authorize")
                .retrieve()
                .body(AutorizadorResponse.class);

        return res != null && res.data().authorization();
    }

}
