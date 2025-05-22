package com.viana.minhas_financas.service;

import com.viana.minhas_financas.dto.*;
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

    @Autowired
    private UsuarioService usuarioService;

    public Carteira criarCarteira(CarteiraRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

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

            dto.setReceitas(carteira.getReceita().stream()
                    .filter(Receita::getReceitaAtiva)
                    .map(ReceitaResponseDTO::new)
                    .toList());

            dto.setDespesas(carteira.getDespesa().stream()
                    .filter(Despesa::getDespesaAtiva)
                    .map(DespesaResponseDTO::new)
                    .toList());
            return dto;
        }).collect(Collectors.toList());
    }

    public Carteira buscarCarteira(Long idCarteira) {
        return carteiraRepository.findById(idCarteira).orElseThrow(() -> new RuntimeException("Carteira não encontrada"));
    }

    public void desativarCarteira(Long idCarteira){
        Carteira carteira = buscarCarteira(idCarteira);

        if (!carteira.getCarteiraAtiva()) {
            throw new RuntimeException("A carteira já esta inativa");
        }
        carteira.setCarteiraAtiva(false);

        Usuario usuario =  carteira.getUsuario();
        if (usuario != null && usuario.getUsuarioAtivo()) {
            usuario.setUsuarioAtivo(false);
            usuarioService.salvarUsuario(usuario);
        }

        salvarCarteira(carteira);
    }

    public Carteira reativarCarteira(Long idCarteira){
        Carteira carteira = buscarCarteira(idCarteira);

        if (carteira.getCarteiraAtiva()) {
            throw new RuntimeException("A carteira já esta ativa");
        }
        carteira.setCarteiraAtiva(true);
        salvarCarteira(carteira);
        return carteira;
    }
}