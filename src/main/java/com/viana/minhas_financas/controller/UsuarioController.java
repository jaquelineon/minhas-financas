package com.viana.minhas_financas.controller;

import com.viana.minhas_financas.dto.UsuarioUpdateDTO;
import com.viana.minhas_financas.model.Usuario;
import com.viana.minhas_financas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PutMapping("/{idCarteira}/nome")
    public ResponseEntity<UsuarioUpdateDTO> editarNome(@PathVariable Long idCarteira, @RequestBody UsuarioUpdateDTO nomeDto) {
        Usuario usuarioAtualizado = usuarioService.editarNome(idCarteira, nomeDto.getNome());

        UsuarioUpdateDTO response = new UsuarioUpdateDTO();
        response.setNome(usuarioAtualizado.getNome());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idCarteira}/email")
    public ResponseEntity<UsuarioUpdateDTO> editarEmail(@PathVariable Long idCarteira, @RequestBody UsuarioUpdateDTO emailDto) {
        Usuario usuario = usuarioService.editarEmail(idCarteira, emailDto.getEmail());

        UsuarioUpdateDTO response = new UsuarioUpdateDTO();
        response.setEmail(usuario.getEmail());

        return ResponseEntity.ok(response);
    }
}
