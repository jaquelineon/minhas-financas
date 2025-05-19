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
    @Enumerated(EnumType.STRING)
    private Categoria categoriaReceita;

    @NotNull(message = "A descrição não pode estar vazia.")
    @Column(nullable = false)
    private String descricaoReceita;

    @ManyToOne
    @JoinColumn(name = "id_carteira")
    private Carteira carteira;

    @Column(nullable = false)
    private Boolean receitaAtiva = true;

    public Receita() {

    }

    public Receita (BigDecimal valorReceita, Categoria categoriaReceita, String descricaoReceita) {
        this.valorReceita = valorReceita;
        this.categoriaReceita = categoriaReceita;
        this.descricaoReceita = descricaoReceita;
    }

    public Long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
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

    public Categoria getCategoriaReceita() {
        return categoriaReceita;
    }

    public void setCategoriaReceita(Categoria categoriaReceita) {
        this.categoriaReceita = categoriaReceita;
    }

    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public void setDescricaoReceita(String descricaoReceita) {
        this.descricaoReceita = descricaoReceita;
    }

    public Boolean getReceitaAtiva() {
        return receitaAtiva;
    }

    public void setReceitaAtiva(Boolean receitaAtiva) {
        this.receitaAtiva = receitaAtiva;
    }
}
