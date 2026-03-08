package com.projetopicpay.springao.dtos;

import java.math.BigDecimal;

public record UsuarioResponseDTO(
        String nome, String email, String documento, BigDecimal saldo) {
}
