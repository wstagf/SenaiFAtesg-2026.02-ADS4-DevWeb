# Passo a passo: Spring Boot + Postgres no Docker + JWT + CRUD

## Passo 1: subir o Postgres no Docker

Crie a pasta do projeto e, dentro dela, o arquivo compose.yaml:

```yaml
services:
  db:
    image: postgres:17
    container_name: loja-db
    environment:
      POSTGRES_DB: loja
      POSTGRES_USER: loja
      POSTGRES_PASSWORD: loja123
    ports:
      - "5432:5432"
    volumes:
      - loja-dados:/var/lib/postgresql/data

volumes:
  loja-dados:
```

## Teste de conexão com o banco
Suba o banco e confira se está no ar:

```shell
docker compose up -d
docker compose ps
docker exec -it loja-db psql -U loja -d loja -c "select version();"
```



## Passo 2: criar o projeto Spring Boot

Gere o projeto em start.spring.io com estas opções:
• Project: Maven. Language: Java. Java: 21.
• Group: com.fatesg.devweb.aula08. Artifact: loja.
• Dependencies: Spring Web, Spring Data JPA, PostgreSQL Driver, Spring Security, Validation.
Descompacte o zip na pasta onde está o compose.yaml. Depois, adicione a biblioteca de JWT dentro de <dependencies> no pom.xml:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```
As demais dependências já vêm com a versão certa pelo Initializr, por isso não é preciso escrever o pom.xml à mão.

## Passo 3: configurar o banco de dados
Em src/main/resources/application.properties:
spring.application.name=loja

``` java
# Banco (mesmos valores do compose.yaml)
spring.datasource.url=jdbc:postgresql://localhost:5432/loja
spring.datasource.username=loja
spring.datasource.password=loja123

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

# JWT
app.jwt.segredo=COLE_AQUI_O_VALOR_GERADO
app.jwt.expiracao-minutos=60
```

## Observação: o valor de app.jwt.segredo deve ser gerado com o comando

```shell
openssl rand -base64 32
```

## Passo 4: entidades com chave estrangeira

A chave estrangeira fica em produtos.categoria_id e é criada pela dupla @ManyToOne + @JoinColumn na entidade Produto.

categoria/Categoria.java:

```java
package com.exemplo.loja.categoria;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String nome;

    protected Categoria() { }

    public Categoria(String nome) { this.nome = nome; }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
```

```java
produto/Produto.java:


package com.exemplo.loja.produto;

import com.exemplo.loja.categoria.Categoria;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    // Chave estrangeira: coluna categoria_id -> categorias.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    protected Produto() { }

    public Produto(String nome, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public Categoria getCategoria() { return categoria; }

    public void atualizar(String nome, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }
}

```

O construtor vazio protected é exigência do JPA. FetchType.LAZY evita carregar a categoria em toda consulta de produto; por isso a conversão para DTO acontece dentro de uma transação, no Passo 5.



## Anotacoes extras

-- Tutoriais de JWT para Spring Boot:
https://medium.com/@victoronu/implementing-jwt-authentication-in-a-simple-spring-boot-application-with-java-b3135dbdb17b


