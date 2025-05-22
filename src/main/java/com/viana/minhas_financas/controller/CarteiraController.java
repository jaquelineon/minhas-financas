package com.viana.minhas_financas.controller;

import com.viana.minhas_financas.dto.*;
import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.model.Receita;
import com.viana.minhas_financas.model.Usuario;
import com.viana.minhas_financas.service.CarteiraService;
import com.viana.minhas_financas.service.DespesaService;
import com.viana.minhas_financas.service.ReceitaService;
import com.viana.minhas_financas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    UsuarioService usuarioService;

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

    @GetMapping("carteiras/{idCarteira}")
    public ResponseEntity<CarteiraResponseDTO> obterCarteira(@PathVariable Long idCarteira) {
        Carteira carteira = carteiraService.buscarCarteira(idCarteira);

        CarteiraResponseDTO response = new CarteiraResponseDTO();
        response.setIdCarteira(carteira.getIdCarteira());
        response.setNomeUsuario(carteira.getUsuario().getNome());
        response.setSaldoCarteira(carteira.getSaldoCarteira());
        response.setReceitas(carteira.getReceita().stream().filter(Receita::getReceitaAtiva).map(ReceitaResponseDTO::new).toList());
        response.setDespesas(carteira.getDespesa().stream().filter(Despesa::getDespesaAtiva).map(DespesaResponseDTO::new).toList());
        response.setEmailUsuario(carteira.getUsuario().getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("carteiras/{idCarteira}/receitas")
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

    @PostMapping("carteiras/{idCarteira}/despesas")
    public ResponseEntity<DespesaResponseDTO> adicionarDespesa(@PathVariable Long idCarteira, @RequestBody DespesaRequestDTO dto) {
        Despesa despesa = despesaService.adicionarDespesa(idCarteira, dto);

        DespesaResponseDTO response = new DespesaResponseDTO();
        response.setIdDespesa(despesa.getIdDespesa());
        response.setValorDespesa(despesa.getValorDespesa());
        response.setCategoriaDespesa(despesa.getCategoriaDespesa());
        response.setDescricaoDespesa(despesa.getDescricaoDespesa());
        response.setIdCarteira(despesa.getCarteira().getIdCarteira());

        return ResponseEntity.ok(response);
    }

    @PutMapping("carteiras/{idCarteira}/receitas/{idReceita}")
    public ResponseEntity<ReceitaUpdateDTO> editarReceita(@PathVariable Long idCarteira, @PathVariable Long idReceita, @RequestBody ReceitaUpdateDTO dto) {
        Receita receita = receitaService.editarReceita(idCarteira, idReceita, dto);

        ReceitaUpdateDTO receitaUpdateDTO = new ReceitaUpdateDTO();
        receitaUpdateDTO.setValorReceita(receita.getValorReceita());
        receitaUpdateDTO.setCategoriaReceita(receita.getCategoriaReceita());
        receitaUpdateDTO.setDescricaoReceita(receita.getDescricaoReceita());
        return ResponseEntity.ok(receitaUpdateDTO);
    }

    @PutMapping("carteiras/{idCarteira}/despesas/{idDespesa}")
    public ResponseEntity<DespesaUpdateDTO> editarDespesa(@PathVariable Long idCarteira, @PathVariable Long idDespesa, @RequestBody DespesaUpdateDTO dto) {
        Despesa despesa = despesaService.editarDespesa(idCarteira, idDespesa, dto);

        DespesaUpdateDTO despesaUpdateDTO = new DespesaUpdateDTO();
        despesaUpdateDTO.setValorDespesa(despesa.getValorDespesa());
        despesaUpdateDTO.setCategoraDespesa(despesa.getCategoriaDespesa());
        despesaUpdateDTO.setDescricaoDespesa(despesa.getDescricaoDespesa());
        return ResponseEntity.ok(despesaUpdateDTO);
    }

    @DeleteMapping("carteiras/{idCarteira}")
    public ResponseEntity<?> desativarCarteira(@PathVariable Long idCarteira) {
        carteiraService.desativarCarteira(idCarteira);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("carteiras/{idCarteira}/receitas/{idReceita}")
    public ResponseEntity<Void> desativarReceita(@PathVariable Long idCarteira, @PathVariable Long idReceita) {
        receitaService.desativarReceita(idCarteira, idReceita);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("carteiras/{idCarteira}/despesas/{idDespesa}")
    public ResponseEntity<Void> desativarDespesa(@PathVariable Long idCarteira, @PathVariable Long idDespesa) {
        despesaService.desativarDespesa(idCarteira, idDespesa);
        return ResponseEntity.ok().build();
    }

    @GetMapping("carteiras/{idCarteira}/reativar")
    public ResponseEntity<CarteiraResponseDTO> reativarCarteira(@PathVariable Long idCarteira) {
        carteiraService.reativarCarteira(idCarteira);

        CarteiraResponseDTO response = new CarteiraResponseDTO();
        Carteira carteira = carteiraService.buscarCarteira(idCarteira);
        response.setIdCarteira(carteira.getIdCarteira());
        response.setSaldoCarteira(carteira.getSaldoCarteira());
        response.setNomeUsuario(carteira.getUsuario().getNome());
        response.setEmailUsuario(carteira.getUsuario().getEmail());
        response.setReceitas(carteira.getReceita().stream()
                .filter(Receita::getReceitaAtiva)
                .map(ReceitaResponseDTO:: new).toList());
        response.setDespesas(carteira.getDespesa().stream()
                .filter(Despesa::getDespesaAtiva)
                .map(DespesaResponseDTO::new).toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("carteiras/{idCarteira}/receitas/{idReceita}/reativar")
    public ResponseEntity<ReceitaResponseDTO> reativarReceita(@PathVariable Long idCarteira, @PathVariable Long idReceita){
        Receita receita = receitaService.reativarReceita(idCarteira, idReceita);

        ReceitaResponseDTO response = new ReceitaResponseDTO();
        response.setIdCarteira(receita.getCarteira().getIdCarteira());
        response.setIdReceita(receita.getIdReceita());
        response.setValorReceita(receita.getValorReceita());
        response.setCategoriaReceita(receita.getCategoriaReceita());
        response.setDescricaoReceita(receita.getDescricaoReceita());

        return ResponseEntity.ok(response);
    }

    @PutMapping("carteiras/{idCarteira}/despesa/{idDespesa}/reativar")
    public ResponseEntity<DespesaResponseDTO> reativarDespesa(@PathVariable Long idCarteira, @PathVariable Long idDespesa) {
        Despesa despesa = despesaService.reativarDespesa(idCarteira, idDespesa);

        DespesaResponseDTO response = new DespesaResponseDTO();
        response.setIdCarteira(despesa.getCarteira().getIdCarteira());
        response.setValorDespesa(despesa.getValorDespesa());
        response.setCategoriaDespesa(despesa.getCategoriaDespesa());
        response.setDescricaoDespesa(despesa.getDescricaoDespesa());

        return ResponseEntity.ok(response);
    }

    @PutMapping("carteiras/usuarios/{idUsuario}/nome")
    public ResponseEntity<UsuarioUpdateDTO> editarNome(@PathVariable Long idUsuario, @RequestBody UsuarioUpdateDTO nomeDto) {
        Usuario usuarioAtualizado = usuarioService.editarNome(idUsuario, nomeDto.getNome());

        UsuarioUpdateDTO response = new UsuarioUpdateDTO();
        response.setNome(usuarioAtualizado.getNome());

        return ResponseEntity.ok(response);
    }
}
