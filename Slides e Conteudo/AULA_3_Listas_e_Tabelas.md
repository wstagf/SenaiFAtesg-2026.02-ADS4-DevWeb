# 📌 AULA 3: Listas, Tabelas e Conteúdo Estruturado

**Objetivo:** Organizar dados com listas e tabelas de forma semântica e profissional

**Nível:** ⭐⭐⭐ Avançado

---

## 🎯 Conceitos Principais

Nesta aula, você aprenderá a:
- Criar listas ordenadas e não-ordenadas
- Usar listas de definição para glossários
- Estruturar dados em tabelas semânticas
- Organizar conteúdo com elementos semânticos
- Usar divs e spans para agrupamentos genéricos

---

## 📚 Tags de Listas

### `<ul>` - Lista Não-Ordenada (com pontos)

Cria uma lista com marcadores/pontos.

```html
<h2>Ingredientes da Receita</h2>
<ul>
    <li>2 xícaras de farinha</li>
    <li>1 copo de leite</li>
    <li>3 ovos</li>
    <li>1 colher de sopa de açúcar</li>
</ul>
```

**Resultado:**
- 2 xícaras de farinha
- 1 copo de leite
- 3 ovos
- 1 colher de sopa de açúcar

---

### `<ol>` - Lista Ordenada (com números)

Cria uma lista numerada.

```html
<h2>Modo de Preparo</h2>
<ol>
    <li>Misture a farinha e o açúcar</li>
    <li>Adicione os ovos um por um</li>
    <li>Acrescente o leite</li>
    <li>Despeje na forma</li>
    <li>Asse a 180°C por 30 minutos</li>
</ol>
```

**Resultado:**
1. Misture a farinha e o açúcar
2. Adicione os ovos um por um
3. Acrescente o leite
4. Despeje na forma
5. Asse a 180°C por 30 minutos

---

### `<li>` - Item de Lista

Define cada item dentro de `<ul>` ou `<ol>`.

**Listas aninhadas (listas dentro de listas):**

```html
<ol>
    <li>Frutas
        <ul>
            <li>Maçã</li>
            <li>Banana</li>
            <li>Laranja</li>
        </ul>
    </li>
    <li>Verduras
        <ul>
            <li>Alface</li>
            <li>Tomate</li>
        </ul>
    </li>
</ol>
```

---

### `<dl>`, `<dt>` e `<dd>` - Lista de Definição

Cria uma lista de termos e suas definições (útil para glossários).

```html
<h2>Glossário de Termos</h2>
<dl>
    <dt>HTML</dt>
    <dd>HyperText Markup Language - Linguagem de marcação para web</dd>
    
    <dt>CSS</dt>
    <dd>Cascading Style Sheets - Usado para estilizar HTML</dd>
    
    <dt>JavaScript</dt>
    <dd>Linguagem de programação para interatividade web</dd>
</dl>
```

**Componentes:**
- `<dl>` - Define o container da lista
- `<dt>` - Define o termo
- `<dd>` - Define a descrição/definição

---

### Listas de Navegação

Listas são perfeitas para menus de navegação:

```html
<nav>
    <ul>
        <li><a href="index.html">Início</a></li>
        <li><a href="produtos.html">Produtos</a></li>
        <li><a href="sobre.html">Sobre</a></li>
        <li><a href="contato.html">Contato</a></li>
    </ul>
</nav>
```

---

## 📊 Tags de Tabelas

### Estrutura Básica

```html
<table>
    <tr>
        <th>Coluna 1</th>
        <th>Coluna 2</th>
        <th>Coluna 3</th>
    </tr>
    <tr>
        <td>Dados 1</td>
        <td>Dados 2</td>
        <td>Dados 3</td>
    </tr>
    <tr>
        <td>Dados 4</td>
        <td>Dados 5</td>
        <td>Dados 6</td>
    </tr>
</table>
```

---

### `<table>` - Container da Tabela

Envolve toda a tabela.

```html
<table>
    <!-- Conteúdo aqui -->
</table>
```

---

### `<tr>` - Linha da Tabela

Define uma linha.

```html
<tr>
    <td>Conteúdo</td>
    <td>Conteúdo</td>
</tr>
```

---

### `<th>` - Célula de Cabeçalho

Define um cabeçalho de coluna (ou linha).

```html
<tr>
    <th>Nome</th>
    <th>Idade</th>
    <th>Cidade</th>
</tr>
```

---

### `<td>` - Célula de Dados

Define uma célula comum de dados.

```html
<tr>
    <td>João</td>
    <td>25</td>
    <td>São Paulo</td>
</tr>
```

---

### `<thead>` - Cabeçalho da Tabela

Agrupa as linhas de cabeçalho (semanticamente correto).

```html
<table>
    <thead>
        <tr>
            <th>Nome</th>
            <th>Nota</th>
        </tr>
    </thead>
    <!-- Resto da tabela -->
</table>
```

---

### `<tbody>` - Corpo da Tabela

Agrupa as linhas de dados.

```html
<table>
    <thead>...</thead>
    <tbody>
        <tr>
            <td>João</td>
            <td>8.5</td>
        </tr>
        <tr>
            <td>Maria</td>
            <td>9.0</td>
        </tr>
    </tbody>
</table>
```

---

### `<tfoot>` - Rodapé da Tabela

Agrupa linhas de resumo/total.

```html
<table>
    <thead>
        <tr>
            <th>Produto</th>
            <th>Preço</th>
            <th>Quantidade</th>
            <th>Total</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Notebook</td>
            <td>R$ 2.000</td>
            <td>2</td>
            <td>R$ 4.000</td>
        </tr>
        <tr>
            <td>Mouse</td>
            <td>R$ 50</td>
            <td>5</td>
            <td>R$ 250</td>
        </tr>
    </tbody>
    <tfoot>
        <tr>
            <th>Total</th>
            <td></td>
            <td></td>
            <td>R$ 4.250</td>
        </tr>
    </tfoot>
</table>
```

---

### `<caption>` - Legenda da Tabela

Adiciona um título descritivo à tabela.

```html
<table>
    <caption>Notas dos Alunos - 2024</caption>
    <thead>
        <tr>
            <th>Nome</th>
            <th>Português</th>
            <th>Matemática</th>
            <th>Média</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>João Silva</td>
            <td>8.0</td>
            <td>7.5</td>
            <td>7.75</td>
        </tr>
    </tbody>
</table>
```

---

### Atributos Úteis de Tabelas

**`colspan`** - Célula ocupa múltiplas colunas:
```html
<tr>
    <th colspan="3">Dados Pessoais</th>
</tr>
```

**`rowspan`** - Célula ocupa múltiplas linhas:
```html
<tr>
    <th rowspan="2">ID</th>
    <th colspan="2">Informações</th>
</tr>
```

---

## 💻 Exemplo de Tabela Completa

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Tabela de Alunos</title>
</head>
<body>
    <h1>Boletim Escolar</h1>
    
    <table>
        <caption>Notas do 2º Trimestre</caption>
        
        <thead>
            <tr>
                <th>Matrícula</th>
                <th>Nome do Aluno</th>
                <th>Português</th>
                <th>Matemática</th>
                <th>Ciências</th>
                <th>Média</th>
            </tr>
        </thead>
        
        <tbody>
            <tr>
                <td>001</td>
                <td>Ana Silva</td>
                <td>8.5</td>
                <td>9.0</td>
                <td>8.0</td>
                <td>8.5</td>
            </tr>
            
            <tr>
                <td>002</td>
                <td>Bruno Santos</td>
                <td>7.0</td>
                <td>6.5</td>
                <td>7.5</td>
                <td>7.0</td>
            </tr>
            
            <tr>
                <td>003</td>
                <td>Carla Costa</td>
                <td>9.5</td>
                <td>9.0</td>
                <td>9.0</td>
                <td>9.17</td>
            </tr>
        </tbody>
        
        <tfoot>
            <tr>
                <th colspan="2">Média da Turma</th>
                <td>8.33</td>
                <td>8.17</td>
                <td>8.17</td>
                <td>8.22</td>
            </tr>
        </tfoot>
    </table>
</body>
</html>
```

---

## 🏗️ Elementos Semânticos Adicionais

### `<main>` - Conteúdo Principal

Define o conteúdo principal da página.

```html
<header>...</header>

<main>
    <section>...</section>
    <section>...</section>
</main>

<footer>...</footer>
```

---

### `<aside>` - Barra Lateral

Define conteúdo secundário ou relacionado.

```html
<main>
    <article>
        <h2>Artigo Principal</h2>
        <p>Conteúdo do artigo...</p>
    </article>
</main>

<aside>
    <h3>Artigos Relacionados</h3>
    <ul>
        <li><a href="#">Artigo 1</a></li>
        <li><a href="#">Artigo 2</a></li>
    </ul>
</aside>
```

---

### `<div>` - Container Genérico

Agrupa conteúdo quando não há elemento semântico apropriado.

```html
<div class="container">
    <h2>Título</h2>
    <p>Conteúdo aqui</p>
</div>
```

---

### `<span>` - Texto Inline Genérico

Agrupa texto inline para estilo ou script.

```html
<p>
    O texto <span>destacado</span> tem uma cor diferente.
</p>
```

---

## ✏️ Exercícios Práticos

### Exercício 1: Lista de Compras
Crie um arquivo `compras.html` com:
- Uma lista `<ul>` com pelo menos 10 itens
- Organize os itens em categorias (aninhadas)
- Use títulos H2 para cada seção

### Exercício 2: Receita com Listas
Crie `receita.html` com:
- Uma `<ul>` para ingredientes
- Uma `<ol>` para modo de preparo
- Pelo menos 2 níveis de aninhamento

### Exercício 3: Glossário
Crie `glossario.html` com:
- Uma `<dl>` com pelo menos 5 termos técnicos
- Cada termo com sua definição

### Exercício 4: Tabela de Horários
Crie `horarios.html` com:
- Uma tabela de horário escolar
- Use `<thead>`, `<tbody>`
- Inclua `<caption>`
- Use `colspan` ou `rowspan` se necessário

### Exercício 5: Tabela de Produtos
Crie `produtos.html` com:
- Tabela de produtos com colunas: Código, Nome, Preço, Quantidade
- Use `<tfoot>` para mostrar totais
- Pelo menos 5 produtos

### Exercício 6: Site Estruturado Completo
Crie um site com:
- `index.html` com listas de categorias
- `tabela.html` com tabela de dados
- Navegação entre as páginas

---

## 🔍 Checklist de Aprendizado

- [ ] Consigo criar listas não-ordenadas `<ul>`
- [ ] Consigo criar listas ordenadas `<ol>`
- [ ] Entendo aninhamento de listas
- [ ] Consigo usar `<dl>`, `<dt>` e `<dd>`
- [ ] Consigo criar uma tabela básica
- [ ] Entendo `<thead>`, `<tbody>` e `<tfoot>`
- [ ] Consigo usar `<caption>` em tabelas
- [ ] Consigo usar `colspan` e `rowspan`
- [ ] Entendo quando usar `<div>` e `<span>`
- [ ] Criei tabelas com dados reais

---

## 📝 Resumo de Tags da Aula 3

| Tag | Função |
|-----|--------|
| `<ul>` | Lista não-ordenada |
| `<ol>` | Lista ordenada |
| `<li>` | Item de lista |
| `<dl>` | Lista de definição |
| `<dt>` | Termo em lista de definição |
| `<dd>` | Descrição do termo |
| `<table>` | Container da tabela |
| `<tr>` | Linha da tabela |
| `<th>` | Cabeçalho de célula |
| `<td>` | Célula de dados |
| `<thead>` | Cabeçalho da tabela |
| `<tbody>` | Corpo da tabela |
| `<tfoot>` | Rodapé da tabela |
| `<caption>` | Legenda da tabela |
| `<main>` | Conteúdo principal |
| `<aside>` | Barra lateral |
| `<div>` | Container genérico |
| `<span>` | Texto inline genérico |

---

## 💡 Boas Práticas

1. **Estrutura semântica:** Sempre use `<thead>`, `<tbody>`, `<tfoot>` em tabelas
2. **Listas para navegação:** Use `<ul>` com `<li>` para menus
3. **Caption em tabelas:** Sempre adicione uma legenda descritiva
4. **Aninhamento:** Mantenha no máximo 2-3 níveis de aninhamento
5. **Dados tabulares:** Use tabelas apenas para dados tabulares, não para layout
6. **Acessibilidade:** Use `<th>` corretamente para que leitores de tela funcionem

---

## 🚀 Próxima Aula

Na **Aula 4**, você aprenderá:
- Como criar formulários interativos
- Incorporar mídia (imagens, vídeos, áudios)
- Elementos avançados e interativos
