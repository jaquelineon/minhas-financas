package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.DespesaRequestDTO;
import com.viana.minhas_financas.dto.DespesaUpdateDTO;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.repository.CarteiraRepository;
import com.viana.minhas_financas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CarteiraService carteiraService;

    public Despesa buscarDespesa(Long idDespesa) {
        return despesaRepository.findById(idDespesa).orElseThrow(()-> new RuntimeException("Despesa não encontrada"));
    }

    public Despesa salvarDespesa(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public Despesa adicionarDespesa(Long idCarteira, DespesaRequestDTO dto) {
        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        Despesa novaDespesa = new Despesa();
        novaDespesa.setValorDespesa(dto.getValorDespesa());
        novaDespesa.setCategoriaDespesa(dto.getCategoriaDespesa());
        novaDespesa.setDescricaoDespesa(dto.getDescricaoDespesa());
        novaDespesa.setCarteira(carteira);
        novaDespesa.setDespesaAtiva(true);

        salvarDespesa(novaDespesa);

        BigDecimal saldoAtual = carteira.getSaldoCarteira() != null ? carteira.getSaldoCarteira() : BigDecimal.ZERO;
        BigDecimal novoSaldo = saldoAtual.subtract(novaDespesa.getValorDespesa());
        carteira.setSaldoCarteira(novoSaldo);

        carteiraService.salvarCarteira(carteira);

        return novaDespesa;
    }

    public Despesa editarDespesa(Long idCarteira, Long idDespesa, DespesaUpdateDTO dto) {
        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        Despesa despesa = buscarDespesa(idDespesa);

        if (!despesa.getCarteira().getIdCarteira().equals(idCarteira)) {
            throw new RuntimeException("A despesa não pertence à carteira informada");
        }
            if (dto.getValorDespesa() != null) {
                despesa.setValorDespesa(dto.getValorDespesa());
            }
            if (dto.getCategoraDespesa() != null) {
                despesa.setCategoriaDespesa(dto.getCategoraDespesa());
            }
            if (dto.getDescricaoDespesa() != null) {
                despesa.setDescricaoDespesa(dto.getDescricaoDespesa());
            }

        despesa.setCarteira(carteira);
        carteiraService.salvarCarteira(carteira);

        return salvarDespesa(despesa);
    }

    public void deletarDespesa(Long idCarteira, Long idDespesa) {
        Despesa despesa = buscarDespesa(idDespesa);

        if (!despesa.getCarteira().getIdCarteira().equals(idCarteira)) {
            throw new RuntimeException("A despesa informada não pertence à carteira.");
        }

        if (!despesa.getDespesaAtiva()) {
            throw new RuntimeException("Despesa inativa");
        }

        despesa.setDespesaAtiva(false);
        salvarDespesa(despesa);

        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        carteira.setSaldoCarteira(carteira.getSaldoCarteira().add(despesa.getValorDespesa()));
        carteiraService.salvarCarteira(carteira);
    }

    public Despesa reativarDespesa(Long idCarteira, Long idDespesa){
        Despesa despesa = buscarDespesa(idDespesa);

        if (despesa.getDespesaAtiva()) {
            throw new RuntimeException("A despesa já esta ativa");
        }

        despesa.setDespesaAtiva(true);
        salvarDespesa(despesa);

        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        carteira.setSaldoCarteira(carteira.getSaldoCarteira().subtract(despesa.getValorDespesa()));
        carteiraService.salvarCarteira(carteira);

        return despesa;
    }
}
