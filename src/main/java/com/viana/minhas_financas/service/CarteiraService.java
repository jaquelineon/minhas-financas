package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.CarteiraRequestDTO;
import com.viana.minhas_financas.dto.CarteiraResponseDTO;
import com.viana.minhas_financas.model.*;
import com.viana.minhas_financas.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    public Carteira criarCarteira(CarteiraRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());

        Carteira carteira = new Carteira();
        carteira.setUsuario(usuario);
        usuario.setCarteira(carteira);

        return carteiraRepository.save(carteira);
    }

    public List<CarteiraResponseDTO> listarCarteiras () {
        List<Carteira> carteiras = carteiraRepository.findAll();
        return carteiras.stream().map(c -> {
            CarteiraResponseDTO dto = new CarteiraResponseDTO();
            dto.setIdCarteira(c.getIdCarteira());
            dto.setNomeUsuario(c.getUsuario().getNome());
            dto.setEmailUsuario(c.getUsuario().getEmail());
            dto.setSaldoCarteira(c.getSaldoCarteira());
            return dto;
        }).collect(Collectors.toList());
    }

    public Carteira salvarCarteira(Carteira carteira) {
        return carteiraRepository.save(carteira);
    }

    public Carteira adicionarReceita(Long idCarteira, Receita receita) {
        Carteira carteira = obterCarteira(idCarteira);
        receita.setCarteira(carteira);
        carteira.getReceita().add(receita);
        carteira.setSaldoCarteira(carteira.getSaldoCarteira().add(receita.getValorReceita()));
        return carteiraRepository.save(carteira);
    }

    public Carteira adicionarDespesa(Long idCarteira, Despesa despesa) {
        Carteira carteira = obterCarteira(idCarteira);
        despesa.setCarteira(carteira);
        carteira.getDespesa().add(despesa);
        carteira.setSaldoCarteira(carteira.getSaldoCarteira().subtract(despesa.getValorDespesa()));
        return carteiraRepository.save(carteira);
    }

    public Carteira obterCarteira(Long idCarteira) {
        return carteiraRepository.findById(idCarteira).orElseThrow(() -> new RuntimeException("Carteira não encontrada."));
    }
}