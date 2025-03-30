package com.viana.minhas_financas.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceita;

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
