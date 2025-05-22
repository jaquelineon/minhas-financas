package com.viana.minhas_financas.repository;

import com.viana.minhas_financas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
