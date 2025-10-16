package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.DespesaRequestDTO;
import com.viana.minhas_financas.dto.DespesaUpdateDTO;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CarteiraService carteiraService;

    public Despesa buscarDespesa(Long idDespesa) {
        return despesaRepository.findById(idDespesa).orElseThrow(()-> new RuntimeException("Despesa não encontrada"));
    }

    public Despesa salvarDespesa(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public Despesa adicionarDespesa(Long idCarteira, DespesaRequestDTO dto) {
        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        Despesa novaDespesa = new Despesa();
        novaDespesa.setValorDespesa(dto.getValorDespesa());
        novaDespesa.setCategoriaDespesa(dto.getCategoriaDespesa());
        novaDespesa.setDescricaoDespesa(dto.getDescricaoDespesa());
        novaDespesa.setCarteira(carteira);
        novaDespesa.setDespesaAtiva(true);

        salvarDespesa(novaDespesa);

        carteiraService.atualizarSaldo(carteira);
        carteiraService.salvarCarteira(carteira);

        return novaDespesa;
    }

    public Despesa editarDespesa(Long idCarteira, Long idDespesa, DespesaUpdateDTO dto) {
        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
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

        // Mantém a referência da carteira
        despesa.setCarteira(carteira);

        // Salva a despesa primeiro
        Despesa despesaAtualizada = salvarDespesa(despesa);

        // Atualiza o saldo da carteira com base nas receitas e despesas atuais
        carteiraService.atualizarSaldo(carteira);

        // Salva a carteira atualizada
        carteiraService.salvarCarteira(carteira);

        return despesaAtualizada;
    }

    public void desativarDespesa(Long idCarteira, Long idDespesa) {
        Despesa despesa = buscarDespesa(idDespesa);

        if (!despesa.getCarteira().getIdCarteira().equals(idCarteira)) {
            throw new RuntimeException("A despesa informada não pertence à carteira.");
        }

        if (!despesa.getDespesaAtiva()) {
            throw new RuntimeException("Despesa inativa");
        }

        despesa.setDespesaAtiva(false);
        salvarDespesa(despesa);

        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        carteiraService.atualizarSaldo(carteira);
        carteiraService.salvarCarteira(carteira);
    }

    public Despesa reativarDespesa(Long idCarteira, Long idDespesa){
        Despesa despesa = buscarDespesa(idDespesa);

        if (despesa.getDespesaAtiva()) {
            throw new RuntimeException("A despesa já esta ativa");
        }

        despesa.setDespesaAtiva(true);
        salvarDespesa(despesa);

        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        carteiraService.atualizarSaldo(carteira);
        carteiraService.salvarCarteira(carteira);

        return despesa;
    }
}
