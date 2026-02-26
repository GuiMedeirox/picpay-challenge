package com.projetopicpay.springao.dtos;

import com.projetopicpay.springao.enums.TipoCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioCadastroDTO(


        @NotBlank
        String nome,

        @NotBlank
        String senha,

        @Email
        @NotBlank
        String email,

        @NotNull
        TipoCliente tipoCliente,

        @NotBlank
        String documento

) {
}
