package com.viana.minhas_financas.repository;

import com.viana.minhas_financas.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
