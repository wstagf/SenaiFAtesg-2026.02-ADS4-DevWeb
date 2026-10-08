package com.fatesg.devweb.aula08.loja.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false)
    private String senha; // hash BCrypt

    protected Usuario() { }

    public Usuario(String email, String senhaCriptografada) {
        this.email = email;
        this.senha = senhaCriptografada;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}