package com.projetopicpay.springao.repositories;

import com.projetopicpay.springao.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, UUID> {




}
