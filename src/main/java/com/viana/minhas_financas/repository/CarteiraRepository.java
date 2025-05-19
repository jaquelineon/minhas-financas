package com.viana.minhas_financas.repository;

import com.viana.minhas_financas.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    List<Carteira> findByCarteiraAtivaTrue();
}
