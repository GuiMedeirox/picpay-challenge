package com.projetopicpay.springao.models;

import com.projetopicpay.springao.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="tb_usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @Column(nullable = false, length = 70)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String documento;

    @Column(nullable = false, unique = true, length = 70)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCliente tipoCliente;


    @PrePersist
    void onCreate(){
        this.saldo = BigDecimal.ZERO;
    }

}
