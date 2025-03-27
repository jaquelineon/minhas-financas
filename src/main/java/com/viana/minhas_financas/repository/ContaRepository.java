package com.viana.minhas_financas.repository;

import com.viana.minhas_financas.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
