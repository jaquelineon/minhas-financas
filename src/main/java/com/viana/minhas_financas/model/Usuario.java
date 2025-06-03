package com.viana.minhas_financas.model;

import com.viana.minhas_financas.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotNull(message = "O nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O e-mail não pode ser nulo.")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private  String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Carteira carteira;

    @Column(nullable = false)
    private Boolean usuarioAtivo = true;

    public Usuario() {}

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.usuarioAtivo = true;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public Boolean getUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(Boolean usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }
}
