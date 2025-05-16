package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.CarteiraResponseDTO;
import com.viana.minhas_financas.dto.ReceitaResponseDTO;
import com.viana.minhas_financas.dto.ReceitaUpdateDTO;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita buscarReceita(Long idReceita) {
        return receitaRepository.findById(idReceita).orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public Receita editarReceita(Long idReceita, ReceitaUpdateDTO dto) {
        Receita receita = receitaRepository.findById(idReceita)
                .orElseThrow(()-> new RuntimeException("Receita não encontrada"));
        receita.setValorReceita(dto.getValorReceita());
        receita.setCategoriaReceita(dto.getCategoriaReceita());
        receita.setDescricaoReceita(dto.getDescricaoReceita());

        return receitaRepository.save(receita);
    }
}
