package com.viana.minhas_financas.service;

import com.viana.minhas_financas.model.*;
import com.viana.minhas_financas.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta criarConta(Conta conta) {
        if (conta.getSaldo() > 0) {
            return contaRepository.save(conta);
        }
        throw new IllegalArgumentException("O saldo inicial da conta não pode ser zerado ou negativo");
    }

    public Conta adicionarReceita(Long idConta, Receita receita) {
        Conta conta = obterConta(idConta);
        receita.setConta(conta);
        conta.getReceita().add(receita);
        conta.setSaldo(conta.getSaldo() + receita.getValor());
        return contaRepository.save(conta);
    }

    public Conta adicionarDespesa(Long idConta, Despesa despesa) {
        Conta conta = obterConta(idConta);
        despesa.setConta(conta);
        conta.getDespesa().add(despesa);
        conta.setSaldo(conta.getSaldo() - despesa.getValor());
        return contaRepository.save(conta);
    }

    public Conta obterConta(Long idConta) {
        return contaRepository.findById(idConta).orElseThrow(() -> new RuntimeException("Conta não encontrada."));
    }
}