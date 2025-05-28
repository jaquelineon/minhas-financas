package com.viana.minhas_financas.service;

import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Usuario;
import com.viana.minhas_financas.repository.CarteiraRepository;
import com.viana.minhas_financas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Usuario buscarUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario salvarUsuario(Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    public Usuario editarNome(Long idCarteira, String nome) {
        Carteira carteira = carteiraRepository.findById(idCarteira).orElseThrow(()-> new RuntimeException("Carteira não encontrada"));
        Usuario usuario = carteira.getUsuario();
        usuario.setNome(nome);
        salvarUsuario(usuario);
        return usuario;
    }

    public Usuario editarEmail(Long idCarteira, String email) {
        Carteira carteira = carteiraRepository.findById(idCarteira).orElseThrow(()-> new RuntimeException("Carteira não encontrada"));
        Usuario usuario = carteira.getUsuario();
        usuario.setEmail(email);
        salvarUsuario(usuario);
        return usuario;
    }
}
