package com.viana.minhas_financas.dto;

import com.viana.minhas_financas.model.Categoria;

import java.math.BigDecimal;

public class DespesaRequestDTO {
    private BigDecimal valorDespesa;
    private Categoria categoriaDespesa;
    private String descricaoDespesa;

    public BigDecimal getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(BigDecimal valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public Categoria getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(Categoria categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }

    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }
}
