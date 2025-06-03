package com.viana.minhas_financas.dto;

import com.viana.minhas_financas.enums.Categoria;

import java.math.BigDecimal;

public class DespesaUpdateDTO {
    private BigDecimal valorDespesa;
    private Categoria categoriaDespesa;
    private String descricaoDespesa;

    public BigDecimal getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(BigDecimal valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public Categoria getCategoraDespesa() {
        return categoriaDespesa;
    }

    public void setCategoraDespesa(Categoria categoraDespesa) {
        this.categoriaDespesa = categoraDespesa;
    }

    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }

    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }
}
