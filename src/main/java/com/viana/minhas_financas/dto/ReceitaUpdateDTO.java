package com.viana.minhas_financas.dto;

import com.viana.minhas_financas.enums.Categoria;

import java.math.BigDecimal;

public class ReceitaUpdateDTO {
    private BigDecimal valorReceita;
    private Categoria categoriaReceita;
    private String descricaoReceita;

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
}
