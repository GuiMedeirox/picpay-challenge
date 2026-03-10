package com.projetopicpay.springao.models;

import com.projetopicpay.springao.enums.StatusTransferencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.lang.annotation.ElementType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_transferencia")
@Getter
@Setter
public class Transferencia {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name="pagador_id")
    private Usuario pagador;


    @ManyToOne
    @JoinColumn(name="recebedor_id")
    private Usuario recebedor;


    @Column(nullable = false)
    private BigDecimal quantia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTransferencia status;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime hora_transacao;

    public Transferencia(Usuario from, Usuario to, BigDecimal quantia, StatusTransferencia status){
        this.pagador=from;
        this.recebedor=to;
        this.quantia=quantia;
        this.status=status;
    }

}
