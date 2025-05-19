package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.*;
import com.viana.minhas_financas.model.*;
import com.viana.minhas_financas.repository.CarteiraRepository;
import com.viana.minhas_financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    public Carteira criarCarteira(CarteiraRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());

        Carteira carteira = new Carteira();
        carteira.setUsuario(usuario);
        usuario.setCarteira(carteira);

        return salvarCarteira(carteira);
    }
    public Carteira salvarCarteira(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    public List<CarteiraResponseDTO> listarCarteiras () {
        List<Carteira> carteirasAtivas = carteiraRepository.findByCarteiraAtivaTrue();
        return carteirasAtivas.stream()
                .map(carteira -> {
            CarteiraResponseDTO dto = new CarteiraResponseDTO();
            dto.setIdCarteira(carteira.getIdCarteira());
            dto.setNomeUsuario(carteira.getUsuario().getNome());
            dto.setEmailUsuario(carteira.getUsuario().getEmail());
            dto.setSaldoCarteira(carteira.getSaldoCarteira());
            dto.setReceitas(carteira.getReceita().stream().map(ReceitaResponseDTO:: new).toList());
            dto.setDespesas(carteira.getDespesa().stream().map(DespesaResposeDTO:: new).toList());
            return dto;
        }).collect(Collectors.toList());
    }

    public Carteira obterCarteira(Long idCarteira) {
        return carteiraRepository.findById(idCarteira).orElseThrow(() -> new RuntimeException("Carteira não encontrada."));
    }

    public void deletarCarteira(Long idCarteira){
        Carteira carteira = obterCarteira(idCarteira);

        if (!carteira.getCarteiraAtiva()) {
            throw new RuntimeException("Carteira inativa");
        }
        carteira.setCarteiraAtiva(false);
        salvarCarteira(carteira);
    }
}