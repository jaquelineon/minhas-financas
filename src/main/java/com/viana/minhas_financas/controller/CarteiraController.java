package com.viana.minhas_financas.controller;

import com.viana.minhas_financas.dto.*;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.repository.CarteiraRepository;
import com.viana.minhas_financas.repository.DespesaRepository;
import com.viana.minhas_financas.repository.ReceitaRepository;
import com.viana.minhas_financas.service.CarteiraService;
import com.viana.minhas_financas.service.DespesaService;
import com.viana.minhas_financas.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private DespesaService despesaService;

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

    @PostMapping("/{idCarteira}/receita")
    public ResponseEntity<ReceitaResponseDTO> adicionarReceita(@PathVariable Long idCarteira, @RequestBody ReceitaRequestDTO dto) {
        Receita receita = receitaService.adicionarReceita(idCarteira, dto);

        ReceitaResponseDTO response = new ReceitaResponseDTO();
        response.setIdReceita(receita.getIdReceita());
        response.setValorReceita(receita.getValorReceita());
        response.setCategoriaReceita(receita.getCategoriaReceita());
        response.setDescricaoReceita(receita.getDescricaoReceita());
        response.setIdCarteira(receita.getCarteira().getIdCarteira());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{idCarteira}/despesa")
    public ResponseEntity<DespesaResposeDTO> adicionarDespesa(@PathVariable Long idCarteira, @RequestBody DespesaRequestDTO dto) {
        Despesa despesa = despesaService.adicionarDespesa(idCarteira, dto);

        DespesaResposeDTO response = new DespesaResposeDTO();
        response.setIdDespesa(despesa.getIdDespesa());
        response.setValorDespesa(despesa.getValorDespesa());
        response.setCategoriaDespesa(despesa.getCategoriaDespesa());
        response.setDescricaoDespesa(despesa.getDescricaoDespesa());
        response.setIdCarteira(despesa.getCarteira().getIdCarteira());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{idCarteira}/receitas/{idReceita}")
    public ResponseEntity<ReceitaUpdateDTO> editarReceita(@PathVariable Long idCarteira, @PathVariable Long idReceita, @RequestBody ReceitaUpdateDTO dto) {
        Receita receita = receitaService.editarReceita(idCarteira, idReceita, dto);

        ReceitaUpdateDTO receitaUpdateDTO = new ReceitaUpdateDTO();
        receitaUpdateDTO.setValorReceita(receita.getValorReceita());
        receitaUpdateDTO.setCategoriaReceita(receita.getCategoriaReceita());
        receitaUpdateDTO.setDescricaoReceita(receita.getDescricaoReceita());
        return ResponseEntity.ok(receitaUpdateDTO);
    }

    @PutMapping("/{idCarteira}/despesas/{idDespesa}")
    public ResponseEntity<DespesaUpdateDTO> editarDespesa(@PathVariable Long idCarteira, @PathVariable Long idDespesa, @RequestBody DespesaUpdateDTO dto) {
        Despesa despesa = despesaService.editarDespesa(idCarteira, idDespesa, dto);

        DespesaUpdateDTO despesaUpdateDTO = new DespesaUpdateDTO();
        despesaUpdateDTO.setValorDespesa(despesa.getValorDespesa());
        despesaUpdateDTO.setCategoraDespesa(despesa.getCategoriaDespesa());
        despesaUpdateDTO.setDescricaoDespesa(despesa.getDescricaoDespesa());
        return ResponseEntity.ok(despesaUpdateDTO);
    }

    @DeleteMapping("/{idCarteira}/deletar")
    public ResponseEntity<?> deletarCarteira(@PathVariable Long idCarteira) {
        carteiraService.deletarCarteira(idCarteira);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{idCarteira}/receitas/deletar/{idReceita}")
    public ResponseEntity<ReceitaResponseDTO> deletarReceita(@PathVariable Long idCarteira, @PathVariable Long idReceita) {
        receitaService.deletarReceita(idCarteira, idReceita);
        return ResponseEntity.ok().build();
    }
}
