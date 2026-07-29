# 📌 AULA 2: Formatação de Texto e Navegação

**Objetivo:** Aprender a formatar texto e criar links para navegação entre páginas

**Nível:** ⭐⭐ Intermediário

---

## 🎯 Conceitos Principais

Nesta aula, você aprenderá a:
- Aplicar estilos de formatação ao texto
- Criar links internos e externos
- Organizar a página com elementos semânticos
- Estruturar navegação entre páginas

---

## 📚 Tags de Formatação de Texto

### `<strong>` vs `<b>` - Negrito

**`<strong>`** - Indica importância semântica (melhor para SEO e acessibilidade)
```html
<p>Esta é uma <strong>informação importante</strong>.</p>
```

**`<b>`** - Apenas estilo visual (sem significado semântico)
```html
<p>Este é um <b>aviso</b>.</p>
```

**Quando usar:** Prefira `<strong>` na maioria dos casos.

---

### `<em>` vs `<i>` - Itálico

**`<em>`** - Ênfase (melhor para SEO e acessibilidade)
```html
<p>Isto é <em>extremamente</em> importante!</p>
```

**`<i>`** - Apenas estilo visual (sem significado semântico)
```html
<p>O nome científico é <i>Canis lupus</i>.</p>
```

**Quando usar:** Use `<em>` para ênfase, `<i>` para nomes científicos, termos estrangeiros, etc.

---

### `<u>` - Sublinhado

```html
<p>Este texto está <u>sublinhado</u>.</p>
```

⚠️ **Atenção:** Use com moderação, pois links também são sublinhados!

---

### `<mark>` - Destaque/Marcador

Marca texto como se tivesse sido destacado com caneta marcadora.

```html
<p>O <mark>Python</mark> é uma linguagem poderosa.</p>
```

---

### `<small>` - Texto Pequeno

Reduz o tamanho do texto. Ideal para avisos ou observações.

```html
<p>Promoção especial por tempo limitado <small>(válido até 31/12)</small></p>
```

---

### `<del>` - Texto Deletado

Mostra texto que foi removido (com linha por cima).

```html
<p>Preço original: <del>R$ 100,00</del></p>
```

---

### `<ins>` - Texto Inserido

Mostra texto que foi adicionado (sublinhado).

```html
<p>Novo preço: <ins>R$ 79,90</ins></p>
```

---

### `<sub>` e `<sup>` - Subscrito e Sobrescrito

```html
<!-- Subscrito (abaixo da linha) -->
<p>A fórmula da água é H<sub>2</sub>O</p>

<!-- Sobrescrito (acima da linha) -->
<p>O resultado é 2<sup>3</sup> = 8</p>
```

---

### `<code>` - Código

Marca código inline (não é para blocos).

```html
<p>Use a função <code>alert()</code> para exibir mensagens.</p>
```

---

### `<pre>` - Texto Pré-formatado

Preserva espaços e quebras de linha (útil para código em blocos).

```html
<pre>
function hello() {
    console.log("Olá, mundo!");
}
</pre>
```

---

### `<blockquote>` - Citação em Bloco

Define uma citação longa.

```html
<blockquote>
    "A educação é a arma mais poderosa que você pode usar para mudar o mundo."
    <br>
    <small>— Nelson Mandela</small>
</blockquote>
```

---

## 🔗 Tags de Links e Navegação

### `<a>` - Link/Âncora

A tag mais importante para navegação!

**Sintaxe:**
```html
<a href="destino.html">Texto do link</a>
```

**Exemplos:**

```html
<!-- Link para outra página do site -->
<a href="sobre.html">Sobre nós</a>

<!-- Link externo (site externo) -->
<a href="https://www.google.com">Google</a>

<!-- Link com alvo em nova aba -->
<a href="https://www.exemplo.com" target="_blank">Abrir em nova aba</a>

<!-- Link para seção da página (âncora) -->
<a href="#secao-contato">Ir para contato</a>

<!-- Link para enviar email -->
<a href="mailto:contato@exemplo.com">Envie um email</a>

<!-- Link para fazer ligação -->
<a href="tel:+5561999999999">Ligar para (61) 99999-9999</a>
```

**Atributos importantes:**
- `href` - Endereço do link (obrigatório)
- `target="_blank"` - Abre em nova aba
- `title="Descrição"` - Tooltip ao passar o mouse

---

### Criando Âncoras (Links Internos)

Primeiro, marque o destino com um `id`:

```html
<h2 id="secao-contato">Entre em Contato</h2>
<p>Aqui estão os dados de contato...</p>
```

Depois, crie um link para aquela seção:

```html
<a href="#secao-contato">Ir para contato</a>
```

---

## 🏗️ Elementos Semânticos de Estrutura

### `<header>` - Cabeçalho

Representa o cabeçalho da página ou uma seção.

```html
<header>
    <h1>Meu Blog</h1>
    <p>Compartilhando conhecimento</p>
</header>
```

---

### `<nav>` - Navegação

Define uma área de navegação.

```html
<nav>
    <ul>
        <li><a href="index.html">Início</a></li>
        <li><a href="sobre.html">Sobre</a></li>
        <li><a href="servicos.html">Serviços</a></li>
        <li><a href="contato.html">Contato</a></li>
    </ul>
</nav>
```

---

### `<section>` - Seção

Define uma seção temática da página.

```html
<section>
    <h2>Sobre nós</h2>
    <p>Somos uma empresa dedicada...</p>
</section>
```

---

### `<article>` - Artigo

Representa um conteúdo independente (post de blog, notícia, etc).

```html
<article>
    <h2>Como aprender HTML</h2>
    <p>Publicado em 15 de janeiro de 2024</p>
    <p>Neste artigo, você aprenderá...</p>
</article>
```

---

### `<footer>` - Rodapé

Rodapé da página ou seção.

```html
<footer>
    <p>&copy; 2024 Meu Site. Todos os direitos reservados.</p>
    <p><a href="privacidade.html">Política de Privacidade</a></p>
</footer>
```

---

## 💻 Exemplo de Página Completa com Navegação

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meu Site</title>
</head>
<body>
    <header>
        <h1>Bem-vindo ao Meu Site</h1>
        <nav>
            <ul>
                <li><a href="index.html">Início</a></li>
                <li><a href="sobre.html">Sobre</a></li>
                <li><a href="#contato">Contato</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <h2>Artigos Recentes</h2>
        
        <article>
            <h3>Meu Primeiro Artigo</h3>
            <p>Este é um <strong>artigo importante</strong> que explica <em>como</em> usar HTML.</p>
            <p>Para mais detalhes, veja <code>&lt;article&gt;</code> tag.</p>
        </article>
        
        <article>
            <h3>Segundo Artigo</h3>
            <p>Aqui temos outro conteúdo interessante.</p>
        </article>
    </section>

    <section id="contato">
        <h2>Entre em Contato</h2>
        <p>Email: <a href="mailto:contato@exemplo.com">contato@exemplo.com</a></p>
        <p>Telefone: <a href="tel:+5561999999999">(61) 99999-9999</a></p>
    </section>

    <footer>
        <p>&copy; 2024 Meu Site. 
            <a href="sobre.html">Sobre</a> | 
            <a href="privacidade.html">Privacidade</a>
        </p>
    </footer>
</body>
</html>
```

---

## ✏️ Exercícios Práticos

### Exercício 1: Página com Múltiplos Links
Crie um arquivo `menu.html` com:
- Um header com título e navegação
- Links para páginas: index.html, sobre.html, contato.html
- Um link externo para Google
- Um link para abrir em nova aba

### Exercício 2: Artigo Formatado
Crie um arquivo `blog.html` com:
- Um article com título
- Parágrafos usando `<strong>`, `<em>` e `<mark>`
- Uma citação usando `<blockquote>`
- Um link para outra página

### Exercício 3: Navegação com Âncoras
Crie `resumo.html` com:
- Uma navegação com links para seções (usando #id)
- Pelo menos 3 seções diferentes
- Um link no final para voltar ao topo

### Exercício 4: Site com 3 Páginas
Crie um mini-site com:
- `index.html` (página inicial)
- `sobre.html` (sobre a empresa)
- `contato.html` (dados de contato)
- Todas com navegação entre elas

---

## 🔍 Checklist de Aprendizado

- [ ] Entendo a diferença entre `<strong>` e `<b>`
- [ ] Sei quando usar `<em>` e `<i>`
- [ ] Consigo criar links simples com `<a>`
- [ ] Sei usar atributo `target="_blank"`
- [ ] Consigo criar links para seções (âncoras)
- [ ] Entendo a estrutura semântica: header, nav, section, article, footer
- [ ] Consigo criar um menu de navegação com `<nav>` e `<ul>`
- [ ] Criei um site com múltiplas páginas conectadas

---

## 📝 Resumo de Tags da Aula 2

| Tag | Função |
|-----|--------|
| `<strong>` | Negrito com importância semântica |
| `<b>` | Negrito visual |
| `<em>` | Itálico com ênfase |
| `<i>` | Itálico visual |
| `<u>` | Sublinhado |
| `<mark>` | Destaque/Marcador |
| `<small>` | Texto pequeno |
| `<del>` | Texto deletado |
| `<ins>` | Texto inserido |
| `<sub>` | Subscrito |
| `<sup>` | Sobrescrito |
| `<code>` | Código inline |
| `<pre>` | Texto pré-formatado |
| `<blockquote>` | Citação em bloco |
| `<a>` | Link/Âncora |
| `<header>` | Cabeçalho |
| `<nav>` | Navegação |
| `<section>` | Seção temática |
| `<article>` | Artigo independente |
| `<footer>` | Rodapé |

---

## 💡 Boas Práticas

1. **Links semânticos:** Use textos descritivos nos links ("Clique aqui" é ruim)
2. **Hierarquia semântica:** Use `<header>`, `<nav>`, `<section>`, `<footer>` para estrutura
3. **Acessibilidade:** Use `title` no `<a>` para descrever o link
4. **Email e telefone:** Use `mailto:` e `tel:` para contato
5. **Formatação:** Use `<strong>` e `<em>` ao invés de `<b>` e `<i>`

---

## 🚀 Próxima Aula

Na **Aula 3**, você aprenderá:
- Como criar listas (ordenadas e não-ordenadas)
- Como estruturar dados em tabelas
- Elementos semânticos avançados
