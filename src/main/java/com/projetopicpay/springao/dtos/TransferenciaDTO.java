package com.projetopicpay.springao.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferenciaDTO(
        @NotBlank String pagador,
        @NotBlank String recebedor,
        @NotNull
        @Positive
        BigDecimal quantia
) {}