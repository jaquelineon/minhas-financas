package com.viana.minhas_financas.service;

import com.viana.minhas_financas.model.Categoria;
import com.viana.minhas_financas.model.Conta;
import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    private void criarConta(double saldoEntrada) {
        if (saldoEntrada <= 0) {
            throw new IllegalArgumentException("O saldo de entrada não pode ser negativo ou zerado");
        }
        Conta conta = new Conta();
        contaRepository.save(conta);
    }

    private void adicionarReceita(Long idConta, Receita receita) {
        Conta conta = obterConta(idConta);
        conta.setReceita(receita);
        contaRepository.save(conta);
    }

    private void adicionarDespesa(Long idConta, Despesa despesa) {
        Conta conta = obterConta(idConta);
        conta.setDespesa(despesa);
        contaRepository.save(conta);
    }

    private Conta obterConta(Long idConta) {
        return contaRepository.getReferenceById(idConta);
    }
}
