package com.viana.minhas_financas.dto;

import com.viana.minhas_financas.model.Despesa;
import com.viana.minhas_financas.model.Receita;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarteiraResponseDTO {
    private Long idCarteira;
    private BigDecimal saldoCarteira;
    private String nomeUsuario;
    private String emailUsuario;
    private List<ReceitaResponseDTO> receitas = new ArrayList<>();
    private List<DespesaResposeDTO> despesas = new ArrayList<>();

    public Long getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Long idCarteira) {
        this.idCarteira = idCarteira;
    }

    public BigDecimal getSaldoCarteira() {
        return saldoCarteira;
    }

    public void setSaldoCarteira(BigDecimal saldoCarteira) {
        this.saldoCarteira = saldoCarteira;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public List<ReceitaResponseDTO> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<ReceitaResponseDTO> receitas) {
        this.receitas = receitas;
    }

    public List<DespesaResposeDTO> getDespesas() {
        return despesas;
    }

    public void setDespesas(List<DespesaResposeDTO> despesas) {
        this.despesas = despesas;
    }
}
