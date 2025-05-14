package com.viana.minhas_financas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceita;

    @NotNull(message = "O valor da Receita não pode ser nulo.")
    @Column(nullable = false)
    private BigDecimal valorReceita;

    @NotNull(message = "É obrigatório escolher uma categoria.")
    @Column(nullable = false)
    private Categoria categoria;

    @NotNull(message = "A descrição não pode estar vazia.")
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_carteira")
    private Carteira carteira;

    public Receita (BigDecimal valorReceita, Categoria categoria, String descricao) {
        this.valorReceita = valorReceita;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public BigDecimal getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(BigDecimal valorReceita) {
        this.valorReceita = valorReceita;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
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
