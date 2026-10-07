package com.fatesg.devweb.aula08.loja.categoria;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String nome;

    protected Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
