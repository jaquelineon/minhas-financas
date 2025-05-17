package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.ReceitaUpdateDTO;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.repository.CarteiraRepository;
import com.viana.minhas_financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    public Receita buscarReceita(Long idReceita) {
        return receitaRepository.findById(idReceita).orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public Receita editarReceita(Long idCarteira, Long idReceita, ReceitaUpdateDTO dto) {
        Carteira carteira = carteiraRepository.findById(idCarteira).orElseThrow(()-> new RuntimeException("Carteira não encontrada"));
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
        carteiraRepository.save(carteira);

        return receitaRepository.save(receita);
    }
}
