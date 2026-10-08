# O que fizemos na aula: API REST com Spring Boot, PostgreSQL, JWT e CRUD

## 1. O objetivo da aula

Nesta aula construímos uma **API REST completa** usando Java e Spring Boot.

A ideia foi juntar vários conceitos em um único projeto:

- Spring Boot para criar a aplicação web;
- PostgreSQL para armazenar os dados;
- Docker para executar o banco de dados;
- Spring Data JPA/Hibernate para conversar com o banco;
- DTOs para controlar os dados que entram e saem da API;
- CRUD para categorias e produtos;
- relacionamento entre tabelas usando chave estrangeira;
- Spring Security para proteger a API;
- BCrypt para armazenar senhas com segurança;
- JWT para autenticar as requisições.

Ao final, o fluxo da aplicação ficou assim:

```text
Cliente
   │
   │ 1. Cadastro
   ▼
/auth/registrar
   │
   │ 2. Login
   ▼
/auth/login
   │
   │ 3. Recebe JWT
   ▼
TOKEN
   │
   │ 4. Authorization: Bearer <token>
   ▼
API protegida
   │
   ├── /categorias
   └── /produtos
           │
           ▼
       PostgreSQL
```

A aplicação, portanto, não é apenas um CRUD. Ela possui também **persistência de dados, relacionamento entre entidades e autenticação**.

---

# 2. A arquitetura que construímos

Organizamos o projeto por responsabilidade:

```text
com.exemplo.loja
├── LojaApplication.java
├── categoria
│   ├── Categoria.java
│   ├── CategoriaRepository.java
│   └── CategoriaController.java
├── produto
│   ├── Produto.java
│   ├── ProdutoRepository.java
│   ├── ProdutoService.java
│   ├── ProdutoController.java
│   └── DTOs
├── usuario
│   ├── Usuario.java
│   ├── UsuarioRepository.java
│   └── UsuarioDetailsService.java
├── auth
│   └── AuthController.java
├── security
│   ├── JwtService.java
│   ├── JwtAuthFilter.java
│   └── SecurityConfig.java
└── erro
    └── TratadorDeErros.java
```

Essa organização nos ajuda a responder uma pergunta importante:

> **Quem é responsável por cada coisa?**

Por exemplo:

- `Controller` recebe requisições HTTP;
- `Service` concentra regras de negócio;
- `Repository` conversa com o banco;
- `Entity` representa os dados persistidos;
- `DTO` define o formato dos dados da API;
- `security` concentra a autenticação e proteção;
- `erro` centraliza alguns tratamentos de exceção.

O Spring Security trabalha justamente com conceitos como autenticação, autorização e uma cadeia de filtros para proteger as requisições. citeturn0search1turn0search3

---

# 3. Primeiro passo: colocar o PostgreSQL no Docker

Antes de criar nossa API, precisávamos de um banco.

Em vez de instalar o PostgreSQL diretamente na máquina, usamos Docker.

O `compose.yaml` definiu:

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
```

## O que isso significa?

Estamos dizendo ao Docker:

> "Crie um container executando PostgreSQL e configure um banco chamado `loja`."

O container possui:

- banco: `loja`;
- usuário: `loja`;
- senha: `loja123`;
- porta: `5432`.

Também criamos um **volume**:

```text
loja-dados
```

O volume é importante porque os dados não ficam simplesmente presos ao ciclo de vida do container.

Podemos pensar assim:

```text
Docker
└── Container loja-db
    └── PostgreSQL
        └── Banco loja
            ├── categorias
            ├── produtos
            └── usuarios
```

Para iniciar:

```bash
docker compose up -d
```

Para verificar:

```bash
docker compose ps
```

E podemos acessar o PostgreSQL dentro do container:

```bash
docker exec -it loja-db psql -U loja -d loja
```

---

# 4. Criando o projeto Spring Boot

Depois criamos o projeto usando o Spring Initializr.

Escolhemos:

- Maven;
- Java 21;
- Spring Web;
- Spring Data JPA;
- PostgreSQL Driver;
- Spring Security;
- Validation.

Cada dependência resolve uma parte do problema.

| Dependência | Responsabilidade |
|---|---|
| Spring Web | Criar endpoints REST |
| Spring Data JPA | Acesso aos dados |
| PostgreSQL Driver | Comunicação com PostgreSQL |
| Spring Security | Segurança e autenticação |
| Validation | Validação dos dados recebidos |
| JJWT | Criar e validar tokens JWT |

O Spring Data JPA fornece a infraestrutura para trabalhar com entidades e repositories, usando JPA/Hibernate como parte da solução de persistência. citeturn0search8

---

# 5. Conectando Spring Boot ao PostgreSQL

No `application.properties`, configuramos:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/loja
spring.datasource.username=loja
spring.datasource.password=loja123
```

Aqui existe uma ideia importante:

```text
Aplicação Spring Boot
        │
        │ JDBC
        ▼
localhost:5432
        │
        ▼
PostgreSQL
        │
        ▼
banco loja
```

Também usamos:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Durante a aula, isso facilitou o desenvolvimento porque o Hibernate pode criar e ajustar as tabelas a partir das entidades.

Para estudo, é bastante conveniente.

Em um sistema de produção, porém, é melhor controlar alterações de banco com ferramentas de migração, como Flyway ou Liquibase.

---

# 6. Criando nossas entidades

Agora começamos a transformar o modelo do problema em classes Java.

Criamos duas entidades principais:

```text
Categoria
Produto
```

O relacionamento escolhido foi:

```text
Categoria 1 ───────── N Produto
```

Ou seja:

> Uma categoria pode possuir vários produtos.

Por exemplo:

```text
Informática
├── Teclado
├── Mouse
├── Monitor
└── Webcam
```

---

# 7. A entidade Categoria

A classe `Categoria` representa a tabela `categorias`.

```java
@Entity
@Table(name = "categorias")
public class Categoria {
```

O `@Entity` informa ao JPA que essa classe representa uma entidade persistente.

O campo:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

representa a chave primária.

Já:

```java
@Column(nullable = false, unique = true, length = 80)
private String nome;
```

define algumas regras:

- não pode ser `null`;
- deve ser único;
- possui limite de tamanho.

---

# 8. A entidade Produto e a chave estrangeira

O produto possui:

```java
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "categoria_id", nullable = false)
private Categoria categoria;
```

Esse é um dos pontos mais importantes da aula.

Estamos dizendo ao JPA:

> "Cada produto pertence a uma categoria."

No banco, isso resulta em algo equivalente a:

```text
produtos
--------------------------------
id
nome
preco
categoria_id  ---> categorias.id
```

Portanto:

```text
categorias
     │
     │ 1
     │
     │ N
     ▼
produtos
```

A anotação:

```java
@ManyToOne
```

representa o relacionamento.

A anotação:

```java
@JoinColumn(name = "categoria_id")
```

indica a coluna usada como chave estrangeira.

---

# 9. Por que usamos `LAZY`?

No produto usamos:

```java
fetch = FetchType.LAZY
```

Isso significa que a categoria não precisa ser carregada imediatamente sempre que buscamos um produto.

A ideia é evitar carregar dados desnecessários.

Por isso também tomamos cuidado para transformar a entidade em DTO dentro da transação.

Esse detalhe apareceu quando trabalhamos com:

```java
ProdutoResponse.de(produto)
```

e acessamos:

```java
p.getCategoria().getNome()
```

---

# 10. Criando os Repositories

Depois das entidades, criamos os repositories.

```java
public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {
}
```

E:

```java
public interface ProdutoRepository
        extends JpaRepository<Produto, Long> {
}
```

A grande vantagem é que não precisamos implementar manualmente operações básicas como:

- inserir;
- buscar;
- atualizar;
- excluir.

O Spring Data JPA fornece essas operações.

Por exemplo:

```java
produtos.findAll();
produtos.findById(id);
produtos.save(produto);
produtos.delete(produto);
```

Também criamos:

```java
List<Produto> findByCategoriaId(Long categoriaId);
```

Isso demonstra uma característica interessante do Spring Data:

> O nome do método pode representar a consulta que queremos realizar.

Assim conseguimos filtrar produtos pela categoria.

---

# 11. Por que usamos DTO?

Não queríamos simplesmente receber e devolver diretamente nossas entidades em todas as operações.

Criamos:

```text
ProdutoRequest
ProdutoResponse
```

O `ProdutoRequest` representa o que o cliente envia:

```java
public record ProdutoRequest(
    String nome,
    BigDecimal preco,
    Long categoriaId
) {}
```

Observe que o cliente não precisa enviar uma categoria inteira.

Ele envia:

```json
{
  "nome": "Teclado",
  "preco": 199.90,
  "categoriaId": 1
}
```

Isso é mais simples e deixa clara a intenção da operação.

Já a resposta pode ser:

```json
{
  "id": 1,
  "nome": "Teclado",
  "preco": 199.90,
  "categoriaId": 1,
  "categoriaNome": "Informática"
}
```

Percebemos então uma diferença:

```text
Request
   ↓
dados necessários para criar/alterar

Response
   ↓
dados que queremos apresentar ao cliente
```

---

# 12. Validação dos dados

Também usamos Bean Validation.

Por exemplo:

```java
@NotBlank
@Size(max = 120)
String nome
```

e:

```java
@NotNull
@Positive
BigDecimal preco
```

Assim a API não aceita qualquer informação.

Por exemplo:

```json
{
  "nome": "",
  "preco": -50,
  "categoriaId": null
}
```

é inválido.

A validação acontece antes de executarmos a regra de negócio.

---

# 13. Por que criamos um Service?

Para produtos criamos:

```text
ProdutoController
        ↓
ProdutoService
        ↓
ProdutoRepository
```

Isso é importante.

O controller não deve concentrar toda a lógica da aplicação.

O controller recebe a requisição e delega:

```java
return service.criar(req);
```

O service então executa as regras.

Por exemplo, para criar um produto:

```java
Categoria categoria = categoriaOu400(req.categoriaId());

Produto salvo = produtos.save(
    new Produto(req.nome(), req.preco(), categoria)
);
```

Primeiro verificamos:

> A categoria realmente existe?

Se não existir:

```text
HTTP 400
Categoria inexistente
```

Só depois criamos o produto.

---

# 14. O CRUD

Criamos os principais endpoints:

| Método | Endpoint | Função |
|---|---|---|
| GET | `/categorias` | Listar categorias |
| GET | `/categorias/{id}` | Buscar categoria |
| POST | `/categorias` | Criar categoria |
| PUT | `/categorias/{id}` | Atualizar categoria |
| DELETE | `/categorias/{id}` | Excluir categoria |
| GET | `/produtos` | Listar produtos |
| GET | `/produtos/{id}` | Buscar produto |
| POST | `/produtos` | Criar produto |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Excluir produto |

Isso forma o CRUD:

```text
C - Create  → POST
R - Read    → GET
U - Update  → PUT
D - Delete  → DELETE
```

---

# 15. Uma regra importante do banco

Existe uma regra que não deixamos apenas na aplicação.

Imagine:

```text
Categoria: Informática
       │
       ├── Teclado
       └── Mouse
```

Se tentarmos excluir `Informática`, o banco precisa impedir isso.

Por quê?

Porque os produtos ainda apontam para aquela categoria.

Isso é responsabilidade da **chave estrangeira**.

O banco protege a integridade dos dados.

Nossa aplicação captura esse erro e transforma em:

```text
HTTP 409 Conflict
```

Assim o cliente recebe uma resposta compreensível.

---

# 16. Criando os usuários

Depois do CRUD, adicionamos autenticação.

Criamos uma tabela:

```text
usuarios
------------------
id
email
senha
```

O e-mail é único:

```java
@Column(nullable = false, unique = true)
private String email;
```

E a senha **não é armazenada em texto puro**.

Em vez disso:

```text
senha digitada
      ↓
BCrypt
      ↓
hash
      ↓
banco
```

Por exemplo:

```text
"segredo123"
       ↓
"$2a$10$...."
```

A aplicação guarda o hash, não a senha original.

---

# 17. O `UserDetailsService`

O Spring Security precisa saber:

> "Onde estão os usuários da minha aplicação?"

Criamos:

```java
UsuarioDetailsService
```

Ele implementa:

```java
UserDetailsService
```

E faz a ponte:

```text
Spring Security
      ↓
UserDetailsService
      ↓
UsuarioRepository
      ↓
PostgreSQL
```

Quando o Spring precisa encontrar um usuário pelo e-mail, nossa implementação consulta o banco.

---

# 18. Cadastro do usuário

O endpoint é:

```text
POST /auth/registrar
```

Recebemos:

```json
{
  "email": "ana@exemplo.com",
  "senha": "segredo123"
}
```

Antes de salvar:

```java
encoder.encode(req.senha())
```

transforma a senha em um hash BCrypt.

Então salvamos:

```java
new Usuario(
    req.email(),
    encoder.encode(req.senha())
)
```

---

# 19. O que acontece no login?

O usuário envia:

```text
POST /auth/login
```

com:

```json
{
  "email": "ana@exemplo.com",
  "senha": "segredo123"
}
```

O `AuthenticationManager` verifica as credenciais.

O fluxo simplificado é:

```text
E-mail + senha
      ↓
AuthenticationManager
      ↓
UserDetailsService
      ↓
Busca usuário
      ↓
PasswordEncoder
      ↓
Compara senha com hash
      ↓
Autenticado?
      │
      ├── NÃO → 401
      │
      └── SIM
           ↓
        gera JWT
```

---

# 20. O que é o JWT?

JWT significa:

**JSON Web Token**

É um token que permite representar informações de autenticação de maneira assinada.

Na nossa aplicação, depois do login:

```text
Usuário
   ↓
login
   ↓
credenciais válidas
   ↓
JWT
```

O cliente recebe algo como:

```text
eyJhbGciOiJIUzI1NiJ9....
```

Esse token será enviado nas próximas requisições.

---

# 21. Como o JWT é usado?

O cliente envia:

```http
Authorization: Bearer <token>
```

Por exemplo:

```http
GET /produtos
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9....
```

O servidor recebe a requisição e o filtro JWT verifica o token.

O Spring Security utiliza filtros para processar requisições e estabelecer a autenticação no contexto de segurança. citeturn0search1

---

# 22. O nosso `JwtAuthFilter`

Criamos:

```text
JwtAuthFilter
```

Ele verifica:

```java
String header =
    request.getHeader(HttpHeaders.AUTHORIZATION);
```

Depois verifica:

```java
header.startsWith("Bearer ")
```

Se existir um token:

```text
Authorization
      ↓
Bearer token
      ↓
extrair token
      ↓
validar assinatura
      ↓
obter e-mail
      ↓
buscar usuário
      ↓
criar Authentication
      ↓
SecurityContext
```

A partir daí, o Spring Security sabe:

> "Esta requisição está autenticada."

---

# 23. O que acontece quando não existe token?

Configuramos:

```java
.requestMatchers("/auth/**", "/error").permitAll()
.anyRequest().authenticated()
```

Isso significa:

```text
/auth/**       → público
/error         → público

qualquer outra rota
               ↓
          precisa estar autenticada
```

Então:

```http
GET /produtos
```

sem token:

```text
401 Unauthorized
```

Com token válido:

```text
200 OK
```

Essa é a principal diferença entre uma API pública e uma API protegida.

---

# 24. Por que usamos `STATELESS`?

Configuramos:

```java
.sessionManagement(s ->
    s.sessionCreationPolicy(
        SessionCreationPolicy.STATELESS
    )
)
```

Isso significa que o servidor não fica mantendo uma sessão tradicional para cada usuário.

Cada requisição carrega sua própria informação de autenticação por meio do token.

Podemos imaginar:

```text
Requisição 1 → Bearer JWT
Requisição 2 → Bearer JWT
Requisição 3 → Bearer JWT
```

O servidor valida o token em cada requisição.

A documentação do Spring Security trata explicitamente da escolha entre autenticação stateful e stateless ao projetar a segurança de uma aplicação. citeturn0search1

---

# 25. Testando tudo

Depois de construir a aplicação, não bastava apenas olhar o código.

Precisávamos provar que tudo funcionava.

## Primeiro: testar sem autenticação

```bash
curl -i http://localhost:8080/produtos
```

Resultado esperado:

```text
401 Unauthorized
```

Isso prova que a rota está protegida.

---

## Segundo: cadastrar usuário

```http
POST /auth/registrar
```

Enviamos:

```json
{
  "email": "ana@exemplo.com",
  "senha": "segredo123"
}
```

Resultado:

```text
201 Created
```

---

## Terceiro: fazer login

```http
POST /auth/login
```

Resultado:

```json
{
  "token": "..."
}
```

Agora temos o JWT.

---

# 26. Criando uma categoria

Com o token:

```http
POST /categorias
Authorization: Bearer <token>
```

Enviamos:

```json
{
  "nome": "Informática"
}
```

Resultado:

```text
201 Created
```

Agora temos:

```text
Categoria 1
Informática
```

---

# 27. Criando um produto

Agora podemos criar:

```http
POST /produtos
Authorization: Bearer <token>
```

com:

```json
{
  "nome": "Teclado",
  "preco": 199.90,
  "categoriaId": 1
}
```

O sistema:

1. recebe o JSON;
2. valida os dados;
3. verifica a categoria;
4. cria o produto;
5. salva no PostgreSQL;
6. devolve a resposta.

---

# 28. Testando o relacionamento

Também testamos:

```http
GET /produtos?categoriaId=1
```

Isso mostra que conseguimos usar a chave estrangeira como filtro.

O relacionamento deixa de ser apenas uma estrutura no banco e passa a fazer parte da API.

---

# 29. Testando a integridade referencial

Tentamos excluir:

```text
Categoria 1
```

enquanto existe:

```text
Produto 1 → Categoria 1
```

O banco impede a operação.

Resultado:

```text
409 Conflict
```

Depois:

```text
excluir Produto 1
```

e finalmente:

```text
excluir Categoria 1
```

Agora a categoria pode ser removida.

Esse teste é importante porque demonstra que a regra está sendo protegida no nível do banco de dados.

---

# 30. O fluxo completo da aplicação

Depois de tudo pronto, podemos visualizar o projeto inteiro:

```text
                    ┌─────────────────┐
                    │     Cliente     │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Controller    │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │     Service     │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Repository    │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   PostgreSQL    │
                    └─────────────────┘
```

Quando existe autenticação:

```text
Cliente
   │
   │ Authorization: Bearer JWT
   ▼
JwtAuthFilter
   │
   ▼
valida token
   │
   ▼
SecurityContext
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
PostgreSQL
```

---

# 31. O que aprendemos de verdade?

Mais importante do que decorar cada classe é entender as responsabilidades.

## Docker

Aprendemos a executar o PostgreSQL de forma isolada.

```text
Docker → infraestrutura
```

## JPA

Aprendemos a mapear objetos Java para tabelas.

```text
Java Object ↔ Banco Relacional
```

## Repository

Aprendemos a encapsular o acesso aos dados.

```text
Repository → persistência
```

## Service

Aprendemos a colocar regras de negócio em um lugar apropriado.

```text
Service → regras
```

## Controller

Aprendemos a expor funcionalidades via HTTP.

```text
Controller → API REST
```

## DTO

Aprendemos a controlar o contrato da API.

```text
DTO → entrada/saída
```

## JWT

Aprendemos a representar a autenticação através de um token.

```text
Login → JWT → requisições autenticadas
```

## Spring Security

Aprendemos a proteger endpoints e controlar quem pode acessar a API.

```text
Security → autenticação/autorização
```

---

# 32. Uma visão de engenharia de software

O mais importante da aula foi perceber que não construímos apenas "um CRUD".

Construímos uma pequena aplicação distribuída em responsabilidades:

```text
                 API REST
                    │
        ┌───────────┴───────────┐
        │                       │
   Segurança                 Negócio
        │                       │
      JWT                   CRUD
        │                       │
   Spring Security          Services
        │                       │
        └───────────┬───────────┘
                    │
                 JPA
                    │
              PostgreSQL
                    │
                 Docker
```

Cada tecnologia resolve um problema diferente.

---

# 33. Os principais conceitos para lembrar

Se você precisar explicar a aula para outra pessoa, lembre desta sequência:

### 1. Infraestrutura

```text
Docker
   ↓
PostgreSQL
```

### 2. Aplicação

```text
Spring Boot
   ↓
API REST
```

### 3. Persistência

```text
JPA/Hibernate
   ↓
Repository
   ↓
PostgreSQL
```

### 4. Modelo

```text
Categoria 1 ─── N Produto
```

### 5. Segurança

```text
Cadastro
   ↓
BCrypt
   ↓
Login
   ↓
JWT
   ↓
Bearer Token
   ↓
Filtro
   ↓
API protegida
```

### 6. Operações

```text
POST   → criar
GET    → consultar
PUT    → atualizar
DELETE → excluir
```

---

# 34. Próximos passos

A aplicação construída já é uma boa base para evoluir.

Os próximos desafios naturais são:

1. **Paginação**
   - Evitar retornar milhares de registros de uma vez.

2. **Perfis de acesso**
   - `USER`;
   - `ADMIN`;
   - permissões diferentes por endpoint.

3. **Flyway ou Liquibase**
   - Controlar a evolução do banco através de migrações.

4. **Testes automatizados**
   - Testes unitários;
   - testes de integração;
   - Testcontainers.

5. **Documentação da API**
   - OpenAPI/Swagger.

6. **Frontend**
   - Consumir essa API usando uma aplicação React/Next.js.

7. **Deploy**
   - Containerizar a aplicação;
   - configurar CI/CD;
   - publicar API e banco em ambiente de produção.

---

# 35. Resumo final

Nesta aula saímos de uma aplicação vazia e chegamos a uma API REST funcional com:

```text
                         ┌───────────────┐
                         │   Cliente     │
                         └───────┬───────┘
                                 │
                          JWT / Bearer
                                 │
                                 ▼
┌─────────────────────────────────────────────────────────┐
│                    Spring Boot API                      │
│                                                         │
│  Security → Controller → Service → Repository → JPA   │
│                                                         │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
                    ┌───────────────┐
                    │  PostgreSQL   │
                    └───────────────┘
                            ▲
                            │
                       Docker
```

O resultado final foi uma API que:

- possui banco de dados;
- possui relacionamento entre tabelas;
- possui CRUD;
- valida entradas;
- trata erros;
- possui usuários;
- armazena senhas com BCrypt;
- possui login;
- gera JWT;
- protege endpoints;
- valida o token nas requisições;
- mantém o banco executando em Docker.

O ponto central da aula foi entender **como essas peças trabalham juntas para formar uma aplicação backend real**.

---

## Fonte da atividade

Este material foi construído a partir do passo a passo desenvolvido na aula, que define como objetivo final uma API REST em Spring Boot com PostgreSQL em Docker, autenticação JWT e CRUD de categorias e produtos relacionados por chave estrangeira.
