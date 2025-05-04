package com.viana.minhas_financas.controller;

import com.viana.minhas_financas.dto.CarteiraRequestDTO;
import com.viana.minhas_financas.dto.CarteiraResponseDTO;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.service.CarteiraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @PostMapping
    public ResponseEntity<CarteiraResponseDTO> criarCarteira(@RequestBody CarteiraRequestDTO dto) {
        Carteira carteira = carteiraService.criarCarteira(dto);

        CarteiraResponseDTO response = new CarteiraResponseDTO();
        response.setIdCarteira(carteira.getIdCarteira());
        response.setSaldo(carteira.getSaldo());
        response.setNomeUsuario(carteira.getUsuario().getNome());
        response.setEmailUsuario(carteira.getUsuario().getEmail());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<Carteira> listarCarteiras () {
        return carteiraService.listarCarteiras();
    }

    @PutMapping("/{id}/receita")
    public Carteira adicionarReceita(@PathVariable Long idCarteira, @RequestBody Receita receita) {
        return carteiraService.adicionarReceita(idCarteira, receita);
    }
}
