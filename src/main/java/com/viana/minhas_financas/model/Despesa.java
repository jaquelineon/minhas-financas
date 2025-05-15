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
    @Enumerated(EnumType.STRING)
    private Categoria categoriaDespesa;

    @NotNull(message = "A descrição não pode estar vazia.")
    @Column(nullable = false)
    private String descricaoDespesa;

    @ManyToOne
    @JoinColumn(name = "id_carteira")
    private Carteira carteira;

    public Despesa(){}

    public Despesa(BigDecimal valorDespesa, Categoria categoriaDespesa, String descricaoDespesa) {
        this.valorDespesa = valorDespesa;
        this.categoriaDespesa = categoriaDespesa;
        this.descricaoDespesa = descricaoDespesa;
    }

    public Long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
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
}
