package com.viana.minhas_financas.controller;

import com.viana.minhas_financas.dto.*;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.repository.CarteiraRepository;
import com.viana.minhas_financas.repository.DespesaRepository;
import com.viana.minhas_financas.repository.ReceitaRepository;
import com.viana.minhas_financas.service.CarteiraService;
import com.viana.minhas_financas.service.ReceitaService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    @PostMapping
    public ResponseEntity<CarteiraResponseDTO> criarCarteira(@RequestBody CarteiraRequestDTO dto) {
        Carteira carteira = carteiraService.criarCarteira(dto);

        CarteiraResponseDTO response = new CarteiraResponseDTO();
        response.setIdCarteira(carteira.getIdCarteira());
        response.setSaldoCarteira(carteira.getSaldoCarteira());
        response.setNomeUsuario(carteira.getUsuario().getNome());
        response.setEmailUsuario(carteira.getUsuario().getEmail());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CarteiraResponseDTO>> listarCarteiras() {
        return ResponseEntity.ok(carteiraService.listarCarteiras());
    }

    @GetMapping("/{idCarteira}")
    public ResponseEntity<CarteiraResponseDTO> obterCarteira(@PathVariable Long idCarteira) {
        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        CarteiraResponseDTO response = new CarteiraResponseDTO();
        response.setIdCarteira(carteira.getIdCarteira());
        response.setNomeUsuario(carteira.getUsuario().getNome());
        response.setSaldoCarteira(carteira.getSaldoCarteira());
        response.setReceitas(carteira.getReceita().stream().map(ReceitaResponseDTO::new).toList());
        response.setDespesas(carteira.getDespesa().stream().map(DespesaResposeDTO::new).toList());
        response.setEmailUsuario(carteira.getUsuario().getEmail());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idCarteira}/deletar")
    public ResponseEntity<?> deletarCarteira(@PathVariable Long idCarteira) {
        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        carteiraRepository.delete(carteira);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idCarteira}/receita")
    public ResponseEntity<ReceitaResponseDTO> adicionarReceita(@PathVariable Long idCarteira, @RequestBody ReceitaResponseDTO dto) {
        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        Receita novaReceita = new Receita();
        ReceitaResponseDTO response = new ReceitaResponseDTO();
        novaReceita.setValorReceita(dto.getValorReceita());
        novaReceita.setCategoriaReceita(dto.getCategoriaReceita());
        novaReceita.setDescricaoReceita(dto.getDescricaoReceita());
        novaReceita.setCarteira(carteira);
        receitaRepository.save(novaReceita);

        BigDecimal saldoAtual = carteira.getSaldoCarteira() != null ? carteira.getSaldoCarteira() : BigDecimal.ZERO;
        carteira.setSaldoCarteira(saldoAtual.add(novaReceita.getValorReceita()));
        carteiraService.salvarCarteira(carteira);

        response.setIdReceita(novaReceita.getIdReceita());
        response.setValorReceita(novaReceita.getValorReceita());
        response.setCategoriaReceita(novaReceita.getCategoriaReceita());
        response.setDescricaoReceita(novaReceita.getDescricaoReceita());
        response.setIdCarteira(novaReceita.getCarteira().getIdCarteira());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{idCarteira}/despesa")
    public ResponseEntity<DespesaResposeDTO> adicionarDespesa(@PathVariable Long idCarteira, @RequestBody DespesaResposeDTO dto) {
        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        Despesa novaDespesa = new Despesa();
        novaDespesa.setValorDespesa(dto.getValorDespesa());
        novaDespesa.setCategoriaDespesa(dto.getCategoriaDespesa());
        novaDespesa.setDescricaoDespesa(dto.getDescricaoDespesa());
        novaDespesa.setCarteira(carteira);
        despesaRepository.save(novaDespesa);

        BigDecimal saldoAtual = carteira.getSaldoCarteira() != null ? carteira.getSaldoCarteira() : BigDecimal.ZERO;
        BigDecimal novoSaldo = saldoAtual.subtract(novaDespesa.getValorDespesa());
        carteira.setSaldoCarteira(novoSaldo);

        carteiraService.salvarCarteira(carteira);

        DespesaResposeDTO response = new DespesaResposeDTO();
        response.setIdDespesa(novaDespesa.getIdDespesa());
        response.setValorDespesa(novaDespesa.getValorDespesa());
        response.setCategoriaDespesa(novaDespesa.getCategoriaDespesa());
        response.setDescricaoDespesa(novaDespesa.getDescricaoDespesa());
        response.setIdCarteira(novaDespesa.getCarteira().getIdCarteira());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idCarteira}/receitas/{idReceita}")
    public ResponseEntity<ReceitaUpdateDTO> editarReceita(@PathVariable Long idCarteira, @PathVariable Long idReceita, @RequestBody ReceitaUpdateDTO dto) {
        Carteira carteira = carteiraService.obterCarteira(idCarteira);
        Receita receita = receitaService.buscarReceita(idReceita);

        if (dto.getValorReceita() != null) {
            receita.setValorReceita(dto.getValorReceita());
        }
        if (dto.getCategoriaReceita() != null) {
            receita.setCategoriaReceita(dto.getCategoriaReceita());
        }
        if (dto.getDescricaoReceita() != null) {
            receita.setDescricaoReceita(dto.getDescricaoReceita());
        }

        receitaRepository.save(receita);
        receita.setCarteira(carteira);
        carteiraRepository.save(carteira);

        ReceitaUpdateDTO updateDTO = new ReceitaUpdateDTO();
        updateDTO.setValorReceita(receita.getValorReceita());
        updateDTO.setCategoriaReceita(receita.getCategoriaReceita());
        updateDTO.setDescricaoReceita(receita.getDescricaoReceita());

        return ResponseEntity.ok(updateDTO);
    }
}
