package com.viana.minhas_financas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDespesa;

    @NotNull(message = "O valor não pode ser nulo.")
    @Column(nullable = false)
    private Double valor;

    @NotNull(message = "É obrigatório escolher uma categoria.")
    @Column(nullable = false)
    private Categoria categoria;

    @NotNull(message = "A descrição não pode estar vazia.")
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    public Despesa(Double valor, Categoria categoria, String descricao) {
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
