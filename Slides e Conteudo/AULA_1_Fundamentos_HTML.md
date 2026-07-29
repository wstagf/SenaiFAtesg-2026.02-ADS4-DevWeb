# 📌 AULA 1: Fundamentos - Tags Estruturais Essenciais

**Objetivo:** Entender a estrutura básica de um documento HTML

**Nível:** ⭐ Iniciante

---

## 🎯 Conceitos Principais

Todo documento HTML segue uma estrutura padrão. Nesta aula, você aprenderá as tags essenciais que formam a base de qualquer página web.

---

## 📚 Tags Estruturais

### `<!DOCTYPE html>`
Declaração que informa ao navegador que se trata de um documento HTML5. Deve estar sempre na primeira linha.

```html
<!DOCTYPE html>
```

### `<html>`
Elemento raiz que envolve todo o documento HTML.

```html
<html>
    <!-- Conteúdo aqui -->
</html>
```

### `<head>`
Seção de cabeçalho que contém metadados e configurações. Não aparece visualmente na página.

```html
<head>
    <meta charset="UTF-8">
    <title>Título da Página</title>
</head>
```

### `<body>`
Contém todo o conteúdo visível da página (textos, imagens, etc).

```html
<body>
    <h1>Bem-vindo!</h1>
    <p>Este é o conteúdo visível.</p>
</body>
```

### `<meta>`
Define metadados sobre a página. Atributos principais:
- `charset="UTF-8"` - Define o conjunto de caracteres
- `name="viewport"` - Torna a página responsiva em celulares

```html
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Descrição da página">
```

### `<title>`
Define o título que aparece na aba do navegador.

```html
<title>Minha Página - Bem-vindo</title>
```

### `<h1>` a `<h6>` - Títulos/Cabeçalhos
Definem títulos de diferentes níveis. `<h1>` é o mais importante, `<h6>` o menos.

```html
<h1>Título Principal (H1)</h1>
<h2>Subtítulo (H2)</h2>
<h3>Sub-subtítulo (H3)</h3>
<h4>Nível 4</h4>
<h5>Nível 5</h5>
<h6>Nível 6</h6>
```

**Boas práticas:**
- Use apenas um `<h1>` por página
- Respeite a hierarquia (não pule de h1 para h3)

### `<p>` - Parágrafo
Define um parágrafo de texto.

```html
<p>Este é um parágrafo de texto.</p>
<p>Este é outro parágrafo.</p>
```

### `<br>` - Quebra de Linha
Cria uma quebra de linha. É uma tag auto-fechável.

```html
<p>Primeira linha<br>Segunda linha</p>
```

### `<hr>` - Linha Horizontal
Cria uma divisão visual com uma linha. É uma tag auto-fechável.

```html
<h2>Seção 1</h2>
<p>Conteúdo da seção 1</p>

<hr>

<h2>Seção 2</h2>
<p>Conteúdo da seção 2</p>
```

---

## 💻 Estrutura Completa de uma Página

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Descrição breve da página">
    <title>Minha Primeira Página</title>
</head>
<body>
    <h1>Bem-vindo ao HTML!</h1>
    
    <h2>O que é HTML?</h2>
    <p>HTML é a linguagem de marcação usada para criar páginas web.</p>
    
    <h2>Estrutura</h2>
    <p>Todo documento HTML segue uma estrutura padronizada.</p>
    
    <hr>
    
    <h2>Próximos Passos</h2>
    <p>Na próxima aula, aprenderemos a formatar textos e criar links.</p>
</body>
</html>
```

---

## ✏️ Exercícios Práticos

### Exercício 1: Criar uma página pessoal
Crie um arquivo `index.html` com:
- Um título na aba
- Um cabeçalho principal com seu nome
- Um parágrafo de apresentação
- Pelo menos 2 seções com subtítulos

### Exercício 2: Página de biografia
Crie uma página `biografia.html` com:
- Título do navegador: "Minha Biografia"
- Um h1 com seu nome
- Usando h2 para as seções: Nascimento, Educação, Hobbies
- Parágrafos em cada seção

### Exercício 3: Estrutura de relatório
Crie um arquivo `relatorio.html` que seja um relatório escolar com:
- Título "Relatório de Português"
- Seções usando h2: Introdução, Desenvolvimento, Conclusão
- Parágrafos sob cada seção
- Uma linha horizontal separando a conclusão

---

## 🔍 Checklist de Aprendizado

- [ ] Entendo o que é `<!DOCTYPE>`
- [ ] Sei a diferença entre `<head>` e `<body>`
- [ ] Consigo criar uma estrutura HTML básica
- [ ] Entendo a hierarquia de títulos (h1 até h6)
- [ ] Consigo usar `<p>`, `<br>` e `<hr>` corretamente
- [ ] Criei e testei meu próprio arquivo HTML

---

## 📝 Resumo de Tags da Aula 1

| Tag | Função | Tipo |
|-----|--------|------|
| `<!DOCTYPE html>` | Declaração de documento | Meta |
| `<html>` | Elemento raiz | Estrutura |
| `<head>` | Cabeçalho (metadados) | Estrutura |
| `<body>` | Corpo (conteúdo) | Estrutura |
| `<meta>` | Metadados | Configuração |
| `<title>` | Título da página | Configuração |
| `<h1>` a `<h6>` | Títulos | Conteúdo |
| `<p>` | Parágrafo | Conteúdo |
| `<br>` | Quebra de linha | Conteúdo |
| `<hr>` | Linha horizontal | Conteúdo |

---

## 💡 Dicas Importantes

1. **Sempre use `<!DOCTYPE html>`** no início do arquivo
2. **Indentação**: Use espaçamento para melhor legibilidade
3. **Atributo `lang`**: Adicione `lang="pt-BR"` em `<html>` para SEO
4. **Charset UTF-8**: Garante suporte a caracteres especiais
5. **Nomes de arquivo**: Use nomes descritivos em minúsculas: `index.html`, `sobre.html`

---

## 🚀 Próxima Aula

Na **Aula 2**, você aprenderá:
- Como formatar textos (negrito, itálico, destaque)
- Como criar links para outras páginas
- Introdução às tags semânticas de navegação
