package com.viana.minhas_financas.controller;

import com.viana.minhas_financas.model.Conta;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        Conta novaConta = contaService.criarConta(conta);
        return ResponseEntity.ok(novaConta);
    }

    @PutMapping("/{id}/receita")
    public Conta adicionarReceita(@PathVariable Long idConta, @RequestBody Receita receita) {
        return contaService.adicionarReceita(idConta, receita);
    }


}
