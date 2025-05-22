package com.viana.minhas_financas.dto;

import java.math.BigDecimal;

public class CarteiraRequestDTO {
    private Long idCarteira;
    private String nome;
    private String email;
    private BigDecimal saldoCarteira;

    public Long getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Long idCarteira) {
        this.idCarteira = idCarteira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSaldoCarteira() {
        return saldoCarteira;
    }

    public void setSaldoCarteira(BigDecimal saldoCarteira) {
        this.saldoCarteira = saldoCarteira;
    }
}
