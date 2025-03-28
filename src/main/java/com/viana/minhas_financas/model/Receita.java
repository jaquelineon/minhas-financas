package com.viana.minhas_financas.model;

import jakarta.persistence.*;

public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceita;
    private Double valor;
    private Categoria categoria;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    public Receita (Double valor, Categoria categoria, String descricao) {
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
