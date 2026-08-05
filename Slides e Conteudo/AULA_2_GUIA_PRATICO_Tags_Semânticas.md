# 📚 GUIA PRÁTICO: Tags Semânticas para Atividade 2

Este guia mostra exemplos práticos de cada tag que você deve usar na Atividade 2.

---

## 1️⃣ HEADER - Cabeçalho da Página

O `<header>` deve conter o título principal e navegação.

### ✅ Exemplo Correto:

```html
<header>
    <h1>João da Silva</h1>
    <p>Desenvolvedor Web Full Stack</p>
    
    <nav>
        <ul>
            <li><a href="#sobre">Sobre</a></li>
            <li><a href="#experiencia">Experiência</a></li>
            <li><a href="#contato">Contato</a></li>
        </ul>
    </nav>
</header>
```

### O que cada tag faz:
- `<header>` - Define o cabeçalho da página
- `<h1>` - Título principal (deve haver apenas um por página)
- `<p>` - Parágrafo descritivo
- `<nav>` - Define a área de navegação
- `<ul>` - Lista não-ordenada (com bullets)
- `<li>` - Item da lista
- `<a>` - Link

---

## 2️⃣ NAV - Navegação com IDs

Links devem apontar para seções usando `#id`.

### ✅ Exemplo Correto:

```html
<!-- Navegação -->
<nav>
    <ul>
        <li><a href="#sobre">Sobre Mim</a></li>
        <li><a href="#experiencia">Experiência</a></li>
        <li><a href="#projetos">Projetos</a></li>
    </ul>
</nav>

<!-- Seções correspondentes -->

<section id="sobre">
    <h2>Sobre Mim</h2>
    <!-- conteúdo -->
</section>

<section id="experiencia">
    <h2>Experiência</h2>
    <!-- conteúdo -->
</section>

<section id="projetos">
    <h2>Projetos</h2>
    <!-- conteúdo -->
</section>
```

**Como funciona:**
- `<a href="#sobre">` aponta para o `id="sobre"`
- Ao clicar, a página salta para aquela seção
- O `#` significa "link para ID"

---

## 3️⃣ SECTION - Seções Temáticas

Cada seção principal deve ter seu próprio `<section>` com um `id`.

### ✅ Exemplo Correto:

```html
<section id="sobre">
    <h2>Sobre Mim</h2>
    <p>Olá, meu nome é...</p>
    <p>Tenho experiência em...</p>
</section>

<section id="habilidades">
    <h2>Habilidades</h2>
    <h3>Linguagens de Programação</h3>
    <ul>
        <li>JavaScript - Avançado</li>
        <li>Python - Intermediário</li>
    </ul>
    
    <h3>Frameworks</h3>
    <ul>
        <li>React - Avançado</li>
        <li>Vue - Intermediário</li>
    </ul>
</section>
```

**Principais características:**
- Cada `<section>` é um bloco temático
- Tem um `id=""` único para navegação
- Contém um `<h2>` com o título da seção
- Pode conter múltiplos `<article>`, parágrafos e listas

---

## 4️⃣ ARTICLE - Artigos/Experiências Individuais

Use `<article>` para cada emprego, projeto ou experiência dentro de uma seção.

### ✅ Exemplo de Seção com Múltiplos Articles:

```html
<section id="experiencia">
    <h2>Experiência Profissional</h2>
    
    <!-- Artigo 1 -->
    <article>
        <h3>Desenvolvedor Senior</h3>
        <p>
            <strong>Empresa:</strong> TechCorp Brasil<br>
            <strong>Período:</strong> 2022-Presente<br>
            <strong>Localização:</strong> São Paulo
        </p>
        <p>Descrição do que você fez neste cargo...</p>
        <ul>
            <li>Responsabilidade 1</li>
            <li>Responsabilidade 2</li>
        </ul>
    </article>
    
    <!-- Artigo 2 -->
    <article>
        <h3>Desenvolvedor Pleno</h3>
        <p>
            <strong>Empresa:</strong> WebDev Inc<br>
            <strong>Período:</strong> 2020-2022
        </p>
        <p>Descrição do que você fez neste cargo...</p>
        <ul>
            <li>Responsabilidade 1</li>
            <li>Responsabilidade 2</li>
        </ul>
    </article>
    
</section>
```

**Por que usar `<article>`:**
- Agrupa conteúdo independente
- Pode ser extraído da página isoladamente
- Ideal para empregos, projetos, posts de blog

---

## 5️⃣ DIV vs SPAN - Containers

`<div>` agrupa blocos de conteúdo, `<span>` agrupa conteúdo inline (dentro de texto).

### ✅ Exemplo Correto:

```html
<!-- DIV - agrupa blocos -->
<div>
    <h3>Informações de Contato</h3>
    <p>Email: joao@email.com</p>
    <p>Telefone: (11) 99999-9999</p>
</div>

<!-- SPAN - dentro de parágrafo -->
<p>
    Meu nome é <span>João da Silva</span> e sou 
    <span>desenvolvedor web</span>.
</p>

<!-- SPAN com informações adicionais -->
<p>
    Tenho experiência em 
    <span>React, Vue.js, Node.js</span>.
</p>
```

**Quando usar cada um:**

| Tag | Quando usar | Exemplo |
|-----|-------------|---------|
| `<div>` | Agrupar blocos de conteúdo | Caixa de informações, seções, grupos de parágrafos |
| `<span>` | Destacar texto dentro de parágrafo | Nomes, palavras-chave, números |

---

## 6️⃣ UL e OL - Listas

`<ul>` para listas sem ordem (bullets), `<ol>` para listas ordenadas (números).

### ✅ Listas Não-Ordenadas (Bullets):

```html
<h3>Habilidades</h3>
<ul>
    <li>HTML5</li>
    <li>CSS3</li>
    <li>JavaScript</li>
    <li>React.js</li>
</ul>
```

Resultado:
```
• HTML5
• CSS3
• JavaScript
• React.js
```

### ✅ Listas Ordenadas (Números):

```html
<h3>Passo a Passo</h3>
<ol>
    <li>Primeira coisa a fazer</li>
    <li>Segunda coisa</li>
    <li>Terceira coisa</li>
</ol>
```

Resultado:
```
1. Primeira coisa a fazer
2. Segunda coisa
3. Terceira coisa
```

### ✅ Listas Aninhadas:

```html
<h3>Tecnologias</h3>
<ul>
    <li>Frontend
        <ul>
            <li>React</li>
            <li>Vue</li>
            <li>Angular</li>
        </ul>
    </li>
    <li>Backend
        <ul>
            <li>Node.js</li>
            <li>Python</li>
        </ul>
    </li>
</ul>
```

---

## 7️⃣ STRONG e EM - Formatação

`<strong>` para informação importante, `<em>` para ênfase.

### ✅ Exemplo Correto:

```html
<p>
    Sou um <strong>desenvolvedor full stack</strong> com 
    <strong>5 anos de experiência</strong> em <em>empresas de grande porte</em>.
</p>

<p>
    Meu trabalho é <em>muito importante</em> para minha carreira 
    porque me permite usar as <strong>tecnologias mais modernas</strong>.
</p>
```

**Diferença semântica:**

| Tag | Significado | Uso |
|-----|-------------|-----|
| `<strong>` | Importância | Datas, nomes, fatos importantes |
| `<em>` | Ênfase | Grifar uma ideia, reforçar conceito |

---

## 8️⃣ Links - Vários Tipos

Existem diferentes tipos de links que você pode usar.

### ✅ Link Simples:

```html
<a href="https://github.com/seu-usuario" target="_blank">
    Meu GitHub
</a>
```

### ✅ Link de Email:

```html
<p>
    Email: <a href="mailto:seu.email@gmail.com">seu.email@gmail.com</a>
</p>
```

### ✅ Link de Telefone:

```html
<p>
    Telefone: <a href="tel:+5511987654321">(11) 98765-4321</a>
</p>
```

### ✅ Link para Seção (Âncora):

```html
<!-- No menu -->
<a href="#contato">Ir para contato</a>

<!-- Na seção -->
<section id="contato">
    <h2>Contato</h2>
    <!-- conteúdo -->
</section>
```

### ✅ Link com nova aba:

```html
<a href="https://www.google.com" target="_blank">
    Google (abre em nova aba)
</a>
```

---

## 9️⃣ FOOTER - Rodapé

Encerra a página com informações finais.

### ✅ Exemplo Correto:

```html
<footer>
    <p>&copy; 2024 João da Silva. Todos os direitos reservados.</p>
    
    <p>
        <a href="#sobre">Sobre</a> |
        <a href="#contato">Contato</a> |
        <a href="privacidade.html">Privacidade</a>
    </p>
    
    <p>
        <small>
            Desenvolvido com HTML puro
        </small>
    </p>
</footer>
```

**O que incluir:**
- Copyright (`&copy;` ou © 2024)
- Links rápidos
- Informações adicionais
- Data de última atualização

---

## 🔟 HR - Divisor Visual

A tag `<hr>` cria uma linha horizontal para separar seções.

### ✅ Exemplo Correto:

```html
<section id="sobre">
    <h2>Sobre Mim</h2>
    <p>Conteúdo aqui...</p>
</section>

<hr>

<section id="experiencia">
    <h2>Experiência</h2>
    <p>Conteúdo aqui...</p>
</section>

<hr>

<section id="contato">
    <h2>Contato</h2>
    <p>Conteúdo aqui...</p>
</section>
```

**Quando usar:**
- Entre seções principais
- Para dividir visualmente o conteúdo
- Ajuda a organizar a leitura

---

## 🎯 Estrutura Completa - Um Exemplo Real

Veja como todas essas tags funcionam juntas:

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Maria - Desenvolvedora</title>
</head>
<body>

    <!-- HEADER COM NAVEGAÇÃO -->
    <header>
        <h1>Maria da Silva</h1>
        <p>Desenvolvedora Full Stack</p>
        
        <nav>
            <ul>
                <li><a href="#sobre">Sobre</a></li>
                <li><a href="#experiencia">Experiência</a></li>
                <li><a href="#projetos">Projetos</a></li>
                <li><a href="#contato">Contato</a></li>
            </ul>
        </nav>
    </header>

    <hr>

    <!-- SEÇÃO SOBRE -->
    <section id="sobre">
        <h2>Sobre Mim</h2>
        <p>
            Olá! Meu nome é Maria da Silva e sou uma 
            <strong>desenvolvedora full stack</strong> com 
            <strong>3 anos de experiência</strong>.
        </p>
        <p>Sou especialista em <em>JavaScript</em> e adoro criar interfaces.</p>
    </section>

    <hr>

    <!-- SEÇÃO EXPERIÊNCIA -->
    <section id="experiencia">
        <h2>Experiência Profissional</h2>
        
        <article>
            <h3>Desenvolvedora Plena na TechCorp</h3>
            <p>
                <strong>Período:</strong> 2022-2024<br>
                <strong>Tecnologias:</strong> React, Node.js
            </p>
            <ul>
                <li>Desenvolveu 5 projetos web</li>
                <li>Liderou equipe de 2 desenvolvedores</li>
            </ul>
        </article>

        <article>
            <h3>Desenvolvedora Junior na WebDev</h3>
            <p>
                <strong>Período:</strong> 2021-2022<br>
                <strong>Tecnologias:</strong> HTML, CSS, JavaScript
            </p>
            <ul>
                <li>Criou 10 páginas web</li>
                <li>Trabalhou com design responsivo</li>
            </ul>
        </article>
    </section>

    <hr>

    <!-- SEÇÃO PROJETOS -->
    <section id="projetos">
        <h2>Projetos</h2>
        
        <article>
            <h3>E-commerce</h3>
            <p>Plataforma de vendas com carrinho e pagamento.</p>
            <p>
                <a href="https://github.com/maria/ecommerce" target="_blank">
                    Ver no GitHub
                </a>
            </p>
        </article>

        <article>
            <h3>Blog Pessoal</h3>
            <p>Blog sobre desenvolvimento web.</p>
            <p>
                <a href="https://maria-blog.com" target="_blank">
                    Visitar blog
                </a>
            </p>
        </article>
    </section>

    <hr>

    <!-- SEÇÃO HABILIDADES -->
    <section id="habilidades">
        <h2>Habilidades</h2>
        
        <h3>Linguagens</h3>
        <ul>
            <li>JavaScript - Avançado</li>
            <li>Python - Intermediário</li>
            <li>SQL - Avançado</li>
        </ul>

        <h3>Frameworks</h3>
        <ul>
            <li>React.js - Avançado</li>
            <li>Vue.js - Básico</li>
        </ul>
    </section>

    <hr>

    <!-- SEÇÃO CONTATO -->
    <section id="contato">
        <h2>Contato</h2>
        
        <div>
            <p>
                Email: <a href="mailto:maria@email.com">maria@email.com</a>
            </p>
            <p>
                Telefone: <a href="tel:+5511987654321">(11) 98765-4321</a>
            </p>
            <p>
                LinkedIn: <a href="https://linkedin.com/in/maria" target="_blank">
                    /in/maria
                </a>
            </p>
        </div>
    </section>

    <hr>

    <!-- FOOTER -->
    <footer>
        <p>&copy; 2024 Maria da Silva. Todos os direitos reservados.</p>
        <p>
            <a href="#sobre">Voltar ao topo</a>
        </p>
    </footer>

</body>
</html>
```

---

## ✨ Dicas Importantes

1. **Não abuse de `<div>`** - Use `<section>`, `<article>`, etc., quando apropriado
2. **Sempre use `<strong>` ao invés de `<b>`** - É mais semântico
3. **Sempre use `<em>` ao invés de `<i>`** - É mais semântico
4. **Teste os links** - Clique em cada um para garantir que funciona
5. **Use IDs descritivos** - `id="sobre"` é melhor que `id="secao1"`
6. **Indente corretamente** - Use 4 espaços para cada nível
7. **Adicione comentários** - Especialmente em estruturas complexas

---

## 🚀 Próximos Passos

Depois de completar a Atividade 2, você poderá:
- Estudar CSS para estilizar essa página
- Adicionar animações e interatividade com JavaScript
- Fazer deploy na internet

Bom trabalho! 🎉

