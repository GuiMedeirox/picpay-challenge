package com.projetopicpay.springao.dtos;

import com.projetopicpay.springao.enums.StatusTransferencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferenciaResponseDTO(UUID uuid, String de, String para, BigDecimal quantia, StatusTransferencia status, LocalDateTime hora) {
}
