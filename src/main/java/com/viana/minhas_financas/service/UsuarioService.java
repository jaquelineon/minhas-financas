package com.viana.minhas_financas.service;

import com.viana.minhas_financas.model.Usuario;
import com.viana.minhas_financas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario buscarUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario editarNome(Long idUsuario, String nome) {
        Usuario usuario = buscarUsuario(idUsuario);
        usuario.setNome(nome);
        salvarUsuario(usuario);
        return usuario;
    }

    public Usuario editarEmail(Long idUsuario, String email) {
        Usuario usuario = buscarUsuario(idUsuario);
        usuario.setEmail(email);
        salvarUsuario(usuario);
        return usuario;
    }

}
