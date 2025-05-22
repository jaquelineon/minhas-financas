package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.ReceitaRequestDTO;
import com.viana.minhas_financas.dto.ReceitaUpdateDTO;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CarteiraService carteiraService;

    public Receita buscarReceita(Long idReceita) {
        return receitaRepository.findById(idReceita).orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public Receita salvarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }

    public Receita adicionarReceita(Long idCarteira, ReceitaRequestDTO dto) {
        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        Receita novaReceita = new Receita();
        novaReceita.setValorReceita(dto.getValorReceita());
        novaReceita.setCategoriaReceita(dto.getCategoriaReceita());
        novaReceita.setDescricaoReceita(dto.getDescricaoReceita());
        novaReceita.setCarteira(carteira);
        novaReceita.setReceitaAtiva(true);

        salvarReceita(novaReceita);

        BigDecimal saldoAtual = carteira.getSaldoCarteira() != null ? carteira.getSaldoCarteira() : BigDecimal.ZERO;
        carteira.setSaldoCarteira(saldoAtual.add(novaReceita.getValorReceita()));
        carteiraService.salvarCarteira(carteira);

        return novaReceita;
    }

    public Receita editarReceita(Long idCarteira, Long idReceita, ReceitaUpdateDTO dto) {
        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        Receita receita = buscarReceita(idReceita);

        if (!receita.getCarteira().getIdCarteira().equals(idCarteira)) {
            throw new RuntimeException("A receita não pertence à carteira informada.");
        }

            if (dto.getValorReceita() != null) {
                receita.setValorReceita(dto.getValorReceita());
            }
            if (dto.getCategoriaReceita() != null) {
                receita.setCategoriaReceita(dto.getCategoriaReceita());
            }
            if (dto.getDescricaoReceita() != null) {
                receita.setDescricaoReceita(dto.getDescricaoReceita());
            }

        receita.setCarteira(carteira);
        carteiraService.salvarCarteira(carteira);

        return salvarReceita(receita);
    }

    public void desativarReceita(Long idCarteira, Long idReceita) {
        Receita receita = buscarReceita(idReceita);

        if (!receita.getCarteira().getIdCarteira().equals(idCarteira)) {
            throw new RuntimeException("A receita informada não pertence à carteira.");
        }

        if (!receita.getReceitaAtiva()) {
            throw new RuntimeException("Receita inativa");
        }

        receita.setReceitaAtiva(false);
        salvarReceita(receita);

        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        carteira.setSaldoCarteira(carteira.getSaldoCarteira().subtract(receita.getValorReceita()));
        carteiraService.salvarCarteira(carteira);
    }

    public Receita reativarReceita(Long idCarteira, Long idReceita) {
        Receita receita = buscarReceita(idReceita);

        if (receita.getReceitaAtiva()) {
            throw new RuntimeException("A receita já esta ativa");
        }
        receita.setReceitaAtiva(true);
        salvarReceita(receita);

        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        carteira.setSaldoCarteira(carteira.getSaldoCarteira().add(receita.getValorReceita()));
        carteiraService.salvarCarteira(carteira);
        return receita;
    }
}
