package com.viana.minhas_financas.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private Double saldo;
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Receita> receita = new ArrayList<>();
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Despesa> despesa = new ArrayList<>();

    public Conta(Usuario usuario, Double saldo) {
        if (saldo <= 0) {
            throw new IllegalArgumentException("Não é possível criar uma conta com saldo zerado" +
                    "ou negativo, deposite um valor");
        }
        this.usuario = usuario;
        this.saldo = saldo;
    }

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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
