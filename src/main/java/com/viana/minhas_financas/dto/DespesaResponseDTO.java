package com.viana.minhas_financas.dto;

import com.viana.minhas_financas.enums.Categoria;
import com.viana.minhas_financas.model.Despesa;

import java.math.BigDecimal;

public class DespesaResponseDTO {
    private Long idDespesa;
    private BigDecimal valorDespesa;
    private Categoria categoriaDespesa;
    private String descricaoDespesa;
    private Long idCarteira;

    public DespesaResponseDTO() {}

    public DespesaResponseDTO(Despesa despesa) {
        this.idDespesa = despesa.getIdDespesa();
        this.valorDespesa = despesa.getValorDespesa();
        this.categoriaDespesa = despesa.getCategoriaDespesa();
        this.descricaoDespesa = despesa.getDescricaoDespesa();
        this.idCarteira = despesa.getCarteira().getIdCarteira();
    }

    public Long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
    }

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

    public Long getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Long idCarteira) {
        this.idCarteira = idCarteira;
    }
}
