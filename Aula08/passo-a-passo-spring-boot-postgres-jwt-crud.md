# Passo a passo: Spring Boot + Postgres no Docker + JWT + CRUD

Oct 7, 2026 · @Thiago

## Visão geral

Ao final você terá uma API REST em Spring Boot, com dados em um Postgres rodando no Docker, login por JWT e um CRUD de duas tabelas ligadas por chave estrangeira: `categorias` (1) e `produtos` (N).

O fluxo é: o cliente se cadastra, faz login, recebe um token e envia esse token no cabeçalho `Authorization: Bearer <token>` em todas as chamadas do CRUD.

| Item | Usado neste guia |
| --- | --- |
| Java | 21 (mínimo 17) |
| Spring Boot | 4.x, gerado pelo [Spring Initializr](https://start.spring.io) |
| Build | Maven |
| Banco | Postgres 17 em Docker |
| JWT | biblioteca jjwt 0.12.6 |
| Pacote base | `com.exemplo.loja` |

Pré-requisitos instalados: JDK, Docker Desktop (ou Docker Engine com Compose) e uma IDE. O código também funciona em Spring Boot 3.5 sem alterações.

Estrutura final de pacotes:

```text
com.exemplo.loja
├── LojaApplication.java
├── categoria   (Categoria, CategoriaRepository, CategoriaController)
├── produto     (Produto, ProdutoRepository, ProdutoService, ProdutoController, DTOs)
├── usuario     (Usuario, UsuarioRepository, UsuarioDetailsService)
├── auth        (AuthController, DTOs)
├── security    (JwtService, JwtAuthFilter, SecurityConfig)
└── erro        (TratadorDeErros)
```

## Passo 1: subir o Postgres no Docker

Crie a pasta do projeto e, dentro dela, o arquivo `compose.yaml`:

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

Suba o banco e confira se está no ar:

```bash
docker compose up -d
docker compose ps
docker exec -it loja-db psql -U loja -d loja -c "select version();"
```

O volume `loja-dados` mantém os dados entre reinícios. Para apagar tudo e recomeçar do zero, use `docker compose down -v`.

Se a porta 5432 já estiver ocupada por outro Postgres na máquina, troque para `"5433:5432"` e use 5433 na URL do Passo 3.

## Passo 2: criar o projeto Spring Boot

Gere o projeto em [start.spring.io](https://start.spring.io) com estas opções:

- Project: Maven. Language: Java. Java: 21.
- Group: `com.exemplo`. Artifact: `loja`.
- Dependencies: Spring Web, Spring Data JPA, PostgreSQL Driver, Spring Security, Validation.

Descompacte o zip na pasta onde está o `compose.yaml`. Depois, adicione a biblioteca de JWT dentro de `<dependencies>` no `pom.xml`:

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

As demais dependências já vêm com a versão certa pelo Initializr, por isso não é preciso escrever o `pom.xml` à mão.

## Passo 3: configurar a conexão com o banco

Em `src/main/resources/application.properties`:

```properties
spring.application.name=loja

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

Gere o segredo do JWT com o comando abaixo e cole o resultado em `app.jwt.segredo`. Ele precisa ter pelo menos 32 bytes, porque o algoritmo HS256 exige chave de 256 bits.

```bash
openssl rand -base64 32
```

`ddl-auto=update` faz o Hibernate criar e ajustar as tabelas a partir das entidades. Serve para estudo; em produção use migrações (Flyway ou Liquibase) e guarde senha e segredo em variáveis de ambiente.

## Passo 4: entidades com chave estrangeira

A chave estrangeira fica em `produtos.categoria_id` e é criada pela dupla `@ManyToOne` + `@JoinColumn` na entidade `Produto`.

`categoria/Categoria.java`:

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

`produto/Produto.java`:

```java
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

O construtor vazio `protected` é exigência do JPA. `FetchType.LAZY` evita carregar a categoria em toda consulta de produto; por isso a conversão para DTO acontece dentro de uma transação, no Passo 5.

## Passo 5: o CRUD

O CRUD expõe `/categorias` e `/produtos` com GET, POST, PUT e DELETE. O produto recebe só o `categoriaId` na entrada e devolve o nome da categoria na saída.

### Repositórios

`categoria/CategoriaRepository.java`:

```java
package com.exemplo.loja.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> { }
```

`produto/ProdutoRepository.java`:

```java
package com.exemplo.loja.produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Filtro pela chave estrangeira
    List<Produto> findByCategoriaId(Long categoriaId);
}
```

### DTOs do produto

`produto/ProdutoRequest.java`:

```java
package com.exemplo.loja.produto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotNull @Positive BigDecimal preco,
        @NotNull Long categoriaId) { }
```

`produto/ProdutoResponse.java`:

```java
package com.exemplo.loja.produto;

import java.math.BigDecimal;

public record ProdutoResponse(Long id, String nome, BigDecimal preco,
                              Long categoriaId, String categoriaNome) {

    public static ProdutoResponse de(Produto p) {
        return new ProdutoResponse(p.getId(), p.getNome(), p.getPreco(),
                p.getCategoria().getId(), p.getCategoria().getNome());
    }
}
```

### Service do produto

`produto/ProdutoService.java`:

```java
package com.exemplo.loja.produto;

import com.exemplo.loja.categoria.Categoria;
import com.exemplo.loja.categoria.CategoriaRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ProdutoService {

    private final ProdutoRepository produtos;
    private final CategoriaRepository categorias;

    public ProdutoService(ProdutoRepository produtos, CategoriaRepository categorias) {
        this.produtos = produtos;
        this.categorias = categorias;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listar(Long categoriaId) {
        List<Produto> lista = (categoriaId == null)
                ? produtos.findAll()
                : produtos.findByCategoriaId(categoriaId);
        return lista.stream().map(ProdutoResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscar(Long id) {
        return ProdutoResponse.de(produtoOu404(id));
    }

    public ProdutoResponse criar(ProdutoRequest req) {
        Categoria categoria = categoriaOu400(req.categoriaId());
        Produto salvo = produtos.save(new Produto(req.nome(), req.preco(), categoria));
        return ProdutoResponse.de(salvo);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest req) {
        Produto produto = produtoOu404(id);
        produto.atualizar(req.nome(), req.preco(), categoriaOu400(req.categoriaId()));
        return ProdutoResponse.de(produto);
    }

    public void excluir(Long id) {
        produtos.delete(produtoOu404(id));
    }

    private Produto produtoOu404(Long id) {
        return produtos.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    private Categoria categoriaOu400(Long id) {
        return categorias.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente"));
    }
}
```

Em `atualizar` não há `save`: a entidade foi carregada dentro da transação, então o Hibernate grava a alteração no commit.

### Controllers

`produto/ProdutoController.java`:

```java
package com.exemplo.loja.produto;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) { this.service = service; }

    @GetMapping
    public List<ProdutoResponse> listar(@RequestParam(required = false) Long categoriaId) {
        return service.listar(categoriaId);
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(@RequestBody @Valid ProdutoRequest req) {
        return service.criar(req);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
```

`categoria/CategoriaController.java` (a categoria é simples, então o controller fala direto com o repositório):

```java
package com.exemplo.loja.categoria;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    public record CategoriaRequest(@NotBlank @Size(max = 80) String nome) { }

    private final CategoriaRepository categorias;

    public CategoriaController(CategoriaRepository categorias) { this.categorias = categorias; }

    @GetMapping
    public List<Categoria> listar() {
        return categorias.findAll();
    }

    @GetMapping("/{id}")
    public Categoria buscar(@PathVariable Long id) {
        return ou404(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria criar(@RequestBody @Valid CategoriaRequest req) {
        return categorias.save(new Categoria(req.nome()));
    }

    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaRequest req) {
        Categoria categoria = ou404(id);
        categoria.setNome(req.nome());
        return categorias.save(categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        categorias.delete(ou404(id));
    }

    private Categoria ou404(Long id) {
        return categorias.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }
}
```

### Erro de chave estrangeira

Excluir uma categoria que ainda tem produtos viola a chave estrangeira, e o banco recusa. Este tratador transforma esse erro (e o de nome duplicado) em HTTP 409.

`erro/TratadorDeErros.java`:

```java
package com.exemplo.loja.erro;

import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> conflito(DataIntegrityViolationException e) {
        return Map.of("erro",
                "Operação viola uma regra do banco (registro duplicado ou em uso por outra tabela)");
    }
}
```

## Passo 6: usuários

O usuário é uma tabela própria, com e-mail único e a senha guardada como hash BCrypt, nunca em texto puro. O nome da tabela é `usuarios` porque `user` é palavra reservada no Postgres.

`usuario/Usuario.java`:

```java
package com.exemplo.loja.usuario;

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
```

`usuario/UsuarioRepository.java`:

```java
package com.exemplo.loja.usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
```

O Spring Security busca usuários por meio de um `UserDetailsService`. Esta classe ensina a ele onde estão os nossos.

`usuario/UsuarioDetailsService.java`:

```java
package com.exemplo.loja.usuario;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarios;

    public UsuarioDetailsService(UsuarioRepository usuarios) { this.usuarios = usuarios; }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarios.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
}
```

## Passo 7: JWT, segurança e login

São quatro peças: quem gera e valida o token, o filtro que lê o token de cada requisição, a configuração que diz quais rotas são abertas, e o controller de cadastro e login.

### Gerar e validar o token

`security/JwtService.java`:

```java
package com.exemplo.loja.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final SecretKey chave;
    private final Duration validade;

    public JwtService(@Value("${app.jwt.segredo}") String segredo,
                      @Value("${app.jwt.expiracao-minutos}") long minutos) {
        this.chave = Keys.hmacShaKeyFor(Decoders.BASE64.decode(segredo));
        this.validade = Duration.ofMinutes(minutos);
    }

    public String gerar(String email) {
        Instant agora = Instant.now();
        return Jwts.builder()
                .subject(email)
                .issuedAt(Date.from(agora))
                .expiration(Date.from(agora.plus(validade)))
                .signWith(chave)
                .compact();
    }

    // Lança JwtException se o token for inválido, adulterado ou estiver expirado
    public String extrairEmail(String token) {
        return Jwts.parser()
                .verifyWith(chave)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
```

### Filtro que autentica cada requisição

`security/JwtAuthFilter.java`:

```java
package com.exemplo.loja.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwt;
    private final UserDetailsService usuarios;

    public JwtAuthFilter(JwtService jwt, UserDetailsService usuarios) {
        this.jwt = jwt;
        this.usuarios = usuarios;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {
            try {
                String email = jwt.extrairEmail(header.substring(7));
                UserDetails usuario = usuarios.loadUserByUsername(email);
                var autenticacao = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            } catch (JwtException | IllegalArgumentException | UsernameNotFoundException e) {
                // Token inválido: segue sem autenticar e a rota protegida responde 401
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }
}
```

### Configuração do Spring Security

`security/SecurityConfig.java`:

```java
package com.exemplo.loja.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/error").permitAll()
                .anyRequest().authenticated())
            .exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
```

O que cada linha da configuração faz:

- `csrf.disable()`: a API não usa cookie de sessão, então a proteção de CSRF não se aplica.
- `STATELESS`: o servidor não guarda sessão; cada requisição se identifica pelo token.
- `/auth/**` fica aberto para cadastro e login; todo o resto exige token.
- `/error` fica aberto para que um 404 ou 400 do controller não vire 401.
- `HttpStatusEntryPoint` faz a falta de token responder 401 em vez do 403 padrão.

### Cadastro e login

`auth/AuthController.java`:

```java
package com.exemplo.loja.auth;

import com.exemplo.loja.security.JwtService;
import com.exemplo.loja.usuario.Usuario;
import com.exemplo.loja.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public record CredenciaisRequest(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 6, max = 72) String senha) { }

    public record TokenResponse(String token) { }

    private final UsuarioRepository usuarios;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(UsuarioRepository usuarios, PasswordEncoder encoder,
                          AuthenticationManager authManager, JwtService jwt) {
        this.usuarios = usuarios;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwt = jwt;
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrar(@RequestBody @Valid CredenciaisRequest req) {
        if (usuarios.existsByEmail(req.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }
        usuarios.save(new Usuario(req.email(), encoder.encode(req.senha())));
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid CredenciaisRequest req) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.senha()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos");
        }
        return new TokenResponse(jwt.gerar(req.email()));
    }
}
```

O `AuthenticationManager` usa o `UsuarioDetailsService` e o `PasswordEncoder` para comparar a senha enviada com o hash salvo. O limite de 72 caracteres na senha existe porque o BCrypt ignora o que passa disso.

## Passo 8: testar

Com o banco no ar, suba a aplicação na raiz do projeto. No Windows, use `mvnw.cmd` no lugar de `./mvnw`.

```bash
./mvnw spring-boot:run
```

No log devem aparecer os `create table` de `categorias`, `produtos` e `usuarios`. Depois, em outro terminal, siga a sequência abaixo.

1. Sem token, a rota protegida responde 401:

   ```bash
   curl -i http://localhost:8080/produtos
   ```

2. Cadastre um usuário (resposta 201):

   ```bash
   curl -i -X POST http://localhost:8080/auth/registrar \
     -H "Content-Type: application/json" \
     -d '{"email":"ana@exemplo.com","senha":"segredo123"}'
   ```

3. Faça login e copie o valor de `token` da resposta:

   ```bash
   curl -X POST http://localhost:8080/auth/login \
     -H "Content-Type: application/json" \
     -d '{"email":"ana@exemplo.com","senha":"segredo123"}'
   ```

4. Guarde o token em uma variável:

   ```bash
   TOKEN=cole_o_token_aqui
   ```

5. Crie uma categoria (ela recebe o id 1):

   ```bash
   curl -X POST http://localhost:8080/categorias \
     -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" \
     -d '{"nome":"Informática"}'
   ```

6. Crie um produto apontando para essa categoria:

   ```bash
   curl -X POST http://localhost:8080/produtos \
     -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" \
     -d '{"nome":"Teclado","preco":199.90,"categoriaId":1}'
   ```

7. Liste, filtre pela chave estrangeira, atualize e exclua:

   ```bash
   curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/produtos
   curl -H "Authorization: Bearer $TOKEN" "http://localhost:8080/produtos?categoriaId=1"

   curl -X PUT http://localhost:8080/produtos/1 \
     -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" \
     -d '{"nome":"Teclado mecânico","preco":349.00,"categoriaId":1}'
   ```

8. Prove a chave estrangeira: excluir a categoria com produto responde 409; depois de excluir o produto, responde 204.

   ```bash
   curl -i -X DELETE -H "Authorization: Bearer $TOKEN" http://localhost:8080/categorias/1
   curl -i -X DELETE -H "Authorization: Bearer $TOKEN" http://localhost:8080/produtos/1
   curl -i -X DELETE -H "Authorization: Bearer $TOKEN" http://localhost:8080/categorias/1
   ```

Para ver a chave estrangeira direto no banco:

```bash
docker exec -it loja-db psql -U loja -d loja -c "\d produtos"
```

No PowerShell, o `curl` com aspas simples e `\` não funciona igual; use o Postman, o Insomnia ou o Git Bash.

## Erros comuns

| Sintoma | Causa provável | Correção |
| --- | --- | --- |
| `Connection refused` ao subir a aplicação | Contêiner do banco parado | `docker compose up -d` e conferir com `docker compose ps` |
| `password authentication failed` | Usuário ou senha diferentes do `compose.yaml`, ou volume antigo com outra senha | Igualar os valores; se mudou a senha depois, `docker compose down -v` e subir de novo |
| Aplicação não sobe, erro de Base64 ou `WeakKeyException` | `app.jwt.segredo` ainda com o texto de exemplo ou com menos de 32 bytes | Gerar com `openssl rand -base64 32` |
| 401 mesmo enviando o token | Falta o prefixo `Bearer `, token expirado ou segredo trocado após o login | Fazer login de novo e conferir o cabeçalho |
| 403 em POST, PUT ou DELETE | CSRF ativo | Conferir o `csrf.disable()` na `SecurityConfig` |
| 400 ao criar produto | `categoriaId` inexistente ou campo inválido | Criar a categoria antes e conferir o JSON |
| `LazyInitializationException` | Categoria lida fora da transação | Converter para DTO dentro do service, como no Passo 5 |
| Porta 5432 ou 8080 em uso | Outro serviço na mesma porta | Trocar a porta no `compose.yaml` ou definir `server.port` |

Próximos passos naturais: paginação nas listagens, perfis de acesso (ADMIN e USER), migrações com Flyway e testes de integração com Testcontainers.

## Fontes

- [Spring Initializr](https://start.spring.io)
- [Spring Security: arquitetura de servlet e filtros](https://docs.spring.io/spring-security/reference/servlet/architecture.html)
- [jjwt no GitHub](https://github.com/jwtk/jjwt)
- [Imagem oficial do Postgres no Docker Hub](https://hub.docker.com/_/postgres)
