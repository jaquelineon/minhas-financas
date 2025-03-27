package com.viana.minhas_financas.model;

import jakarta.persistence.*;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    private Usuario usuario;
    private Double saldo;
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private Receita receita;
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private Despesa despesa;

    public Long getId() {
        return idConta;
    }

    public void setId(Long id) {
        this.idConta = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
