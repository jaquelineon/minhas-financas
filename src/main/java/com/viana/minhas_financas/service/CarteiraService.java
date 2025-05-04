package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.CarteiraRequestDTO;
import com.viana.minhas_financas.model.*;
import com.viana.minhas_financas.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Carteira> listarCarteiras () {
        return carteiraRepository.findAll();
    }

    public Carteira adicionarReceita(Long idCarteira, Receita receita) {
        Carteira carteira = obterCarteira(idCarteira);
        receita.setCarteira(carteira);
        carteira.getReceita().add(receita);
        carteira.setSaldo(carteira.getSaldo() + receita.getValor());
        return carteiraRepository.save(carteira);
    }

    public Carteira adicionarDespesa(Long idCarteira, Despesa despesa) {
        Carteira carteira = obterCarteira(idCarteira);
        despesa.setCarteira(carteira);
        carteira.getDespesa().add(despesa);
        carteira.setSaldo(carteira.getSaldo() - despesa.getValor());
        return carteiraRepository.save(carteira);
    }

    public Carteira obterCarteira(Long idCarteira) {
        return carteiraRepository.findById(idCarteira).orElseThrow(() -> new RuntimeException("Carteira não encontrada."));
    }
}