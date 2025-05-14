package com.viana.minhas_financas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDespesa;

    @NotNull(message = "O valor da Despesa não pode ser nulo.")
    @Column(nullable = false)
    private BigDecimal valorDespesa;

    @NotNull(message = "É obrigatório escolher uma categoria.")
    @Column(nullable = false)
    private Categoria categoria;

    @NotNull(message = "A descrição não pode estar vazia.")
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_carteira")
    private Carteira carteira;

    public Despesa(BigDecimal valorDespesa, Categoria categoria, String descricao) {
        this.valorDespesa = valorDespesa;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public BigDecimal getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(BigDecimal valorDespesa) {
        this.valorDespesa = valorDespesa;
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
