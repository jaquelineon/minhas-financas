package com.viana.minhas_financas.controller;

import com.viana.minhas_financas.dto.LoginRequestDTO;
import com.viana.minhas_financas.dto.TokenResponseDTO;
import com.viana.minhas_financas.model.Usuario;
import com.viana.minhas_financas.repository.UsuarioRepository;
import com.viana.minhas_financas.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.email());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email não encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        boolean senhaCorreta = passwordEncoder.matches(loginRequest.senha(), usuario.getSenha());

        if (!senhaCorreta) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida");
        }
        String token = jwtService.gerarToken(usuario.getEmail(), usuario.getRole());
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
