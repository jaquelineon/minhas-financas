package com.viana.minhas_financas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarteira;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @NotNull(message = "O campo usuario é obrigatório")
    private Usuario usuario;
    private BigDecimal saldoCarteira;
    @OneToMany(mappedBy = "carteira", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receita> receita = new ArrayList<>();
    @OneToMany(mappedBy = "carteira", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Despesa> despesa = new ArrayList<>();
    @Column(nullable = false)
    private Boolean carteiraAtiva = true;

    public Carteira() {

    }

    public Carteira(Usuario usuario, BigDecimal saldoCarteira) {
        this.usuario = usuario;
        this.saldoCarteira = saldoCarteira;
        this.carteiraAtiva = true;
    }

    public Long getIdCarteira() {
        return idCarteira;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Receita> getReceita() {
        return receita;
    }

    public void setReceita(List<Receita> receita) {
        this.receita = receita;
    }

    public List<Despesa> getDespesa() {
        return despesa;
    }

    public void setDespesa(List<Despesa> despesa) {
        this.despesa = despesa;
    }

    public BigDecimal getSaldoCarteira() {
        return saldoCarteira;
    }

    public void setSaldoCarteira(BigDecimal saldoCarteira) {
        this.saldoCarteira = saldoCarteira;
    }

    public Boolean getCarteiraAtiva() {
        return carteiraAtiva;
    }

    public void setCarteiraAtiva(Boolean carteiraAtiva) {
        this.carteiraAtiva = carteiraAtiva;
    }
}
