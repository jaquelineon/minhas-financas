package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.DespesaUpdateDTO;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.repository.CarteiraRepository;
import com.viana.minhas_financas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    public Despesa buscarDespesa(Long idDespesa) {
        return despesaRepository.findById(idDespesa).orElseThrow(()-> new RuntimeException("Despesa não encontrada"));
    }

    public Despesa editarDespesa(Long idCarteira, Long idDespesa, DespesaUpdateDTO dto) {
        Carteira carteira = carteiraRepository.findById(idCarteira).orElseThrow(()-> new RuntimeException("Carteira não encontrada"));
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
        carteiraRepository.save(carteira);

        return despesaRepository.save(despesa);
    }
}
