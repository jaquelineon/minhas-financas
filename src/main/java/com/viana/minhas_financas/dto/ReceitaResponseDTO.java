package com.viana.minhas_financas.dto;

import com.viana.minhas_financas.model.Carteira;
import com.viana.minhas_financas.model.Categoria;
import com.viana.minhas_financas.model.Receita;

import java.math.BigDecimal;
import java.util.Locale;

public class ReceitaResponseDTO {
    private Long idReceita;
    private BigDecimal valorReceita;
    private Categoria categoriaReceita;
    private String descricaoReceita;
    private Long idCarteira;

    public ReceitaResponseDTO(){}

    public ReceitaResponseDTO(Receita receita) {
            this.idReceita = receita.getIdReceita();
            this.valorReceita = receita.getValorReceita();
            this.categoriaReceita = receita.getCategoriaReceita();
            this.descricaoReceita = receita.getDescricaoReceita();
            this.idCarteira = receita.getCarteira().getIdCarteira();
    }

    public Long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public BigDecimal getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(BigDecimal valorReceita) {
        this.valorReceita = valorReceita;
    }

    public Categoria getCategoriaReceita() {
        return categoriaReceita;
    }

    public void setCategoriaReceita(Categoria categoriaReceita) {
        this.categoriaReceita = categoriaReceita;
    }

    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public void setDescricaoReceita(String descricaoReceita) {
        this.descricaoReceita = descricaoReceita;
    }

    public Long getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Long idCarteira) {
        this.idCarteira = idCarteira;
    }
}
