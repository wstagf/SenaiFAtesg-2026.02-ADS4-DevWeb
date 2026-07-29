# 📌 AULA 4: Formulários, Multimedia e Elementos Avançados

**Objetivo:** Coletar dados do usuário, incorporar mídia e usar elementos interativos avançados

**Nível:** ⭐⭐⭐⭐ Complexo

---

## 🎯 Conceitos Principais

Nesta aula, você aprenderá a:
- Criar formulários completos para coletar dados
- Usar diferentes tipos de inputs
- Incorporar imagens, vídeos e áudios
- Usar elementos interativos e semânticos avançados
- Estruturar mídia de forma responsiva

---

## 📝 Tags de Formulário

### `<form>` - Container do Formulário

Envolve todos os elementos do formulário.

```html
<form action="processar.php" method="POST">
    <!-- Campos do formulário aqui -->
    <button type="submit">Enviar</button>
</form>
```

**Atributos principais:**
- `action` - URL que processa os dados
- `method` - GET ou POST
- `name` - Nome do formulário

---

### `<label>` - Rótulo

Associa um texto a um input para melhor acessibilidade.

```html
<label for="nome">Nome Completo:</label>
<input type="text" id="nome" name="nome">
```

**Importante:** Use `for` para vincular ao `id` do input.

---

### `<input>` - Campo de Entrada

A tag mais importante de formulário. Tem vários tipos:

#### Texto Simples
```html
<label for="email">Email:</label>
<input type="text" id="email" name="email" placeholder="seu@email.com">
```

#### Email (com validação)
```html
<input type="email" id="email" name="email" required>
```

#### Senha
```html
<label for="senha">Senha:</label>
<input type="password" id="senha" name="senha" required>
```

#### Número
```html
<label for="idade">Idade:</label>
<input type="number" id="idade" name="idade" min="0" max="150">
```

#### Data
```html
<label for="nascimento">Data de Nascimento:</label>
<input type="date" id="nascimento" name="nascimento">
```

#### Hora
```html
<label for="horario">Horário:</label>
<input type="time" id="horario" name="horario">
```

#### URL
```html
<input type="url" id="website" name="website" placeholder="https://exemplo.com">
```

#### Telefone
```html
<input type="tel" id="telefone" name="telefone" placeholder="(61) 99999-9999">
```

#### Checkbox (múltipla seleção)
```html
<fieldset>
    <legend>Interesses:</legend>
    
    <label>
        <input type="checkbox" name="interesses" value="esportes">
        Esportes
    </label>
    
    <label>
        <input type="checkbox" name="interesses" value="musica">
        Música
    </label>
    
    <label>
        <input type="checkbox" name="interesses" value="leitura">
        Leitura
    </label>
</fieldset>
```

#### Radio Button (seleção única)
```html
<fieldset>
    <legend>Gênero:</legend>
    
    <label>
        <input type="radio" name="genero" value="masculino">
        Masculino
    </label>
    
    <label>
        <input type="radio" name="genero" value="feminino">
        Feminino
    </label>
    
    <label>
        <input type="radio" name="genero" value="outro">
        Outro
    </label>
</fieldset>
```

#### Arquivo (Upload)
```html
<label for="foto">Upload sua foto:</label>
<input type="file" id="foto" name="foto" accept="image/*">
```

#### Range (Controle Deslizante)
```html
<label for="volume">Volume:</label>
<input type="range" id="volume" name="volume" min="0" max="100" value="50">
```

#### Cor
```html
<label for="cor">Escolha uma cor:</label>
<input type="color" id="cor" name="cor" value="#FF5733">
```

#### Submit (Botão de Envio)
```html
<input type="submit" value="Enviar Formulário">
```

#### Reset (Limpar Formulário)
```html
<input type="reset" value="Limpar">
```

#### Hidden (Campo Oculto)
```html
<input type="hidden" name="id_sessao" value="12345">
```

---

### Atributos Importantes

- `required` - Campo obrigatório
- `placeholder` - Texto de dica
- `value` - Valor padrão
- `disabled` - Desabilita o campo
- `readonly` - Apenas leitura
- `min` e `max` - Valores mínimo e máximo
- `pattern` - Expressão regular para validação
- `autocomplete` - Auto-completar

---

### `<textarea>` - Área de Texto

Para textos longos, multi-linha.

```html
<label for="mensagem">Sua Mensagem:</label>
<textarea 
    id="mensagem" 
    name="mensagem" 
    rows="5" 
    cols="40"
    placeholder="Digite sua mensagem aqui..."
    required>
</textarea>
```

---

### `<select>` e `<option>` - Lista Suspensa

```html
<label for="pais">País:</label>
<select id="pais" name="pais">
    <option value="">-- Selecione um país --</option>
    <option value="br">Brasil</option>
    <option value="pt">Portugal</option>
    <option value="us">Estados Unidos</option>
    <option value="mx">México</option>
</select>
```

---

### `<optgroup>` - Agrupamento de Opções

Agrupa opções em categorias.

```html
<select name="veiculo">
    <optgroup label="Carros">
        <option>Sedan</option>
        <option>SUV</option>
        <option>Hatchback</option>
    </optgroup>
    <optgroup label="Motos">
        <option>Scooter</option>
        <option>Trail</option>
    </optgroup>
</select>
```

---

### `<button>` - Botão

Alternativa mais semântica ao `<input type="submit">`.

```html
<button type="submit">Enviar</button>
<button type="reset">Limpar</button>
<button type="button">Clique Aqui</button>
```

---

### `<fieldset>` e `<legend>` - Agrupamento

Agrupa campos relacionados com borda e legenda.

```html
<fieldset>
    <legend>Dados de Contato</legend>
    
    <label for="email">Email:</label>
    <input type="email" id="email" name="email">
    
    <label for="telefone">Telefone:</label>
    <input type="tel" id="telefone" name="telefone">
</fieldset>
```

---

### `<datalist>` - Auto-completar

Sugestões para um input de texto.

```html
<label for="navegador">Navegador favorito:</label>
<input list="navegadores" id="navegador" name="navegador">

<datalist id="navegadores">
    <option value="Chrome">
    <option value="Firefox">
    <option value="Safari">
    <option value="Edge">
</datalist>
```

---

### `<output>` - Resultado de Cálculo

Mostra o resultado de um cálculo.

```html
<form oninput="resultado.value=parseInt(a.value)+parseInt(b.value)">
    <input type="number" id="a" value="0"> +
    <input type="number" id="b" value="0"> =
    <output name="resultado">0</output>
</form>
```

---

## 💻 Exemplo de Formulário Completo

```html
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Formulário de Cadastro</title>
</head>
<body>
    <h1>Cadastro de Usuário</h1>
    
    <form action="cadastro.php" method="POST">
        
        <!-- Dados Pessoais -->
        <fieldset>
            <legend>Dados Pessoais</legend>
            
            <label for="nome">Nome Completo:</label>
            <input type="text" id="nome" name="nome" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="telefone">Telefone:</label>
            <input type="tel" id="telefone" name="telefone">
            
            <label for="data_nasc">Data de Nascimento:</label>
            <input type="date" id="data_nasc" name="data_nasc">
        </fieldset>
        
        <!-- Endereço -->
        <fieldset>
            <legend>Endereço</legend>
            
            <label for="rua">Rua:</label>
            <input type="text" id="rua" name="rua">
            
            <label for="numero">Número:</label>
            <input type="number" id="numero" name="numero">
            
            <label for="cidade">Cidade:</label>
            <input type="text" id="cidade" name="cidade">
            
            <label for="estado">Estado:</label>
            <select id="estado" name="estado">
                <option value="">Selecione...</option>
                <option value="DF">Distrito Federal</option>
                <option value="SP">São Paulo</option>
                <option value="MG">Minas Gerais</option>
            </select>
        </fieldset>
        
        <!-- Preferências -->
        <fieldset>
            <legend>Preferências</legend>
            
            <label>Gênero:</label>
            <label>
                <input type="radio" name="genero" value="masculino">
                Masculino
            </label>
            <label>
                <input type="radio" name="genero" value="feminino">
                Feminino
            </label>
            <label>
                <input type="radio" name="genero" value="outro">
                Prefiro não informar
            </label>
            
            <br><br>
            
            <label>Interesses:</label>
            <label>
                <input type="checkbox" name="interesses" value="tecnologia">
                Tecnologia
            </label>
            <label>
                <input type="checkbox" name="interesses" value="esportes">
                Esportes
            </label>
            <label>
                <input type="checkbox" name="interesses" value="musica">
                Música
            </label>
            
            <br><br>
            
            <label for="newsletter">
                <input type="checkbox" id="newsletter" name="newsletter">
                Receber newsletter por email
            </label>
        </fieldset>
        
        <!-- Mensagem -->
        <fieldset>
            <legend>Mensagem</legend>
            
            <label for="mensagem">Deixe uma mensagem:</label>
            <textarea id="mensagem" name="mensagem" rows="5" cols="40"></textarea>
        </fieldset>
        
        <!-- Botões -->
        <button type="submit">Cadastrar</button>
        <button type="reset">Limpar Formulário</button>
        <button type="button">Cancelar</button>
    </form>
</body>
</html>
```

---

## 🖼️ Tags de Multimedia

### `<img>` - Imagem

A tag mais importante para inserir imagens.

```html
<img src="foto.jpg" alt="Descrição da foto">
```

**Atributos:**
- `src` - Caminho da imagem (obrigatório)
- `alt` - Texto alternativo (obrigatório para acessibilidade)
- `width` - Largura
- `height` - Altura
- `title` - Tooltip ao passar o mouse

```html
<img 
    src="imagens/perfil.jpg" 
    alt="Foto de perfil de João" 
    width="200" 
    height="200"
    title="Clique para ampliar"
>
```

---

### `<picture>` - Imagem Responsiva

Define múltiplas versões de uma imagem para diferentes tamanhos de tela.

```html
<picture>
    <source media="(max-width: 600px)" srcset="imagem-mobile.jpg">
    <source media="(max-width: 1200px)" srcset="imagem-tablet.jpg">
    <img src="imagem-desktop.jpg" alt="Descrição">
</picture>
```

---

### `<figure>` e `<figcaption>` - Figura com Legenda

```html
<figure>
    <img src="grafico.png" alt="Gráfico de vendas">
    <figcaption>Gráfico de vendas - Q1 2024</figcaption>
</figure>
```

---

### `<video>` - Vídeo

```html
<video width="400" height="300" controls>
    <source src="video.mp4" type="video/mp4">
    <source src="video.webm" type="video/webm">
    Seu navegador não suporta vídeo HTML5.
</video>
```

**Atributos:**
- `controls` - Mostra controles de reprodução
- `autoplay` - Inicia automaticamente
- `loop` - Repete o vídeo
- `muted` - Som desligado
- `poster="imagem.jpg"` - Imagem de preview

**Exemplo com todos os atributos:**
```html
<video 
    width="500" 
    height="300" 
    controls 
    autoplay 
    loop 
    muted
    poster="preview.jpg">
    <source src="video.mp4" type="video/mp4">
    <source src="video.webm" type="video/webm">
</video>
```

---

### `<audio>` - Áudio

```html
<audio controls>
    <source src="musica.mp3" type="audio/mpeg">
    <source src="musica.ogg" type="audio/ogg">
    Seu navegador não suporta áudio HTML5.
</audio>
```

**Atributos similares ao vídeo:**
- `controls` - Mostra controles
- `autoplay` - Inicia automaticamente
- `loop` - Repete
- `muted` - Som desligado

---

### `<track>` - Legenda para Vídeo

Adiciona legendas ou descrição ao vídeo.

```html
<video width="400" height="300" controls>
    <source src="video.mp4" type="video/mp4">
    <track kind="subtitles" src="legenda-pt.vtt" srclang="pt" label="Português">
    <track kind="subtitles" src="legenda-en.vtt" srclang="en" label="English">
</video>
```

---

### `<iframe>` - Frame Incorporado

Incorpora conteúdo externo (YouTube, mapas, etc).

```html
<!-- YouTube -->
<iframe 
    width="560" 
    height="315" 
    src="https://www.youtube.com/embed/dQw4w9WgXcQ"
    frameborder="0" 
    allow="autoplay; encrypted-media" 
    allowfullscreen>
</iframe>

<!-- Google Maps -->
<iframe 
    width="400" 
    height="300" 
    src="https://www.google.com/maps/embed?pb=..."
    allowfullscreen="" 
    loading="lazy">
</iframe>
```

---

### `<svg>` - Gráfico Vetorial Inline

Define gráficos vetoriais diretamente no HTML.

```html
<svg width="100" height="100">
    <circle cx="50" cy="50" r="40" fill="blue" />
    <rect x="10" y="10" width="30" height="30" fill="red" />
</svg>
```

---

### `<canvas>` - Desenho com JavaScript

```html
<canvas id="meuCanvas" width="400" height="300"></canvas>

<script>
    var canvas = document.getElementById("meuCanvas");
    var ctx = canvas.getContext("2d");
    ctx.fillStyle = "blue";
    ctx.fillRect(10, 10, 150, 100);
</script>
```

---

## 🎛️ Elementos Interativos Avançados

### `<details>` e `<summary>` - Conteúdo Expansível

```html
<details>
    <summary>Clique para expandir</summary>
    <p>Este conteúdo aparece quando você clica.</p>
    <p>Pode conter múltiplos parágrafos.</p>
</details>
```

---

### `<time>` - Data/Hora

```html
<p>
    Evento: <time datetime="2024-12-25">25 de dezembro de 2024</time>
</p>

<p>
    Horário: <time>14:30</time>
</p>
```

---

### `<address>` - Informação de Contato

```html
<address>
    <p>Empresa XYZ</p>
    <p>Rua Principal, 123 - São Paulo, SP</p>
    <p>Email: <a href="mailto:contato@exemplo.com">contato@exemplo.com</a></p>
</address>
```

---

### `<progress>` - Barra de Progresso

```html
<label for="download">Download:</label>
<progress id="download" value="70" max="100"></progress>
```

---

### `<meter>` - Medida/Gauge

```html
<label for="temperatura">Temperatura:</label>
<meter id="temperatura" value="6" min="0" max="10"></meter>

<label for="capacidade">Capacidade de Armazenamento:</label>
<meter value="6" min="0" max="10" low="3" high="7" optimum="9"></meter>
```

---

### `<wbr>` - Quebra de Palavra Possível

```html
<p>
    Este é um texto muito longo sem espaços que pode quebrar em:
    <wbr>
    múltiplas linhas no navegador quando necessário.
</p>
```

---

## ✏️ Exercícios Práticos

### Exercício 1: Formulário Básico
Crie `contato.html` com:
- Campos: nome, email, assunto
- Um textarea para mensagem
- Botão de envio

### Exercício 2: Formulário de Cadastro
Crie `cadastro.html` com:
- Múltiplos fieldsets (dados pessoais, endereço, preferências)
- Pelo menos 5 tipos diferentes de inputs
- Validação com `required`

### Exercício 3: Galeria de Imagens
Crie `galeria.html` com:
- Pelo menos 6 imagens
- Use `<figure>` e `<figcaption>`
- Imagens responsivas com `<picture>`

### Exercício 4: Página com Vídeo
Crie `videos.html` com:
- Um ou dois vídeos incorporados
- Use atributos `controls`, `autoplay`, `loop`
- Adicione legendas com `<track>`

### Exercício 5: Página Interativa
Crie `interativo.html` com:
- Elemento `<details>` para FAQ
- Barra `<progress>`
- Elemento `<meter>`
- Um `<iframe>` de mapa ou vídeo

### Exercício 6: Site Completo
Junte tudo num site com:
- Página inicial com listas e navegação
- Página de formulário de contato
- Galeria de imagens
- Página com elementos interativos

---

## 🔍 Checklist de Aprendizado

- [ ] Consigo criar um formulário básico
- [ ] Entendo os diferentes tipos de `<input>`
- [ ] Sei usar `<textarea>` e `<select>`
- [ ] Consigo usar `<fieldset>` e `<legend>`
- [ ] Entendo validação com `required`
- [ ] Consigo inserir imagens com `<img>`
- [ ] Sei usar `<picture>` para imagens responsivas
- [ ] Consigo incorporar vídeos com `<video>`
- [ ] Consigo incorporar áudios com `<audio>`
- [ ] Sei usar `<iframe>` para incorporar conteúdo
- [ ] Consigo usar `<details>` para conteúdo expansível
- [ ] Entendo `<time>`, `<progress>` e `<meter>`
- [ ] Criei formulários e páginas com multimedia

---

## 📝 Resumo de Tags da Aula 4

| Tag | Função |
|-----|--------|
| `<form>` | Container do formulário |
| `<label>` | Rótulo de input |
| `<input>` | Campo de entrada (múltiplos tipos) |
| `<textarea>` | Área de texto multi-linha |
| `<select>` | Lista suspensa |
| `<option>` | Opção em select |
| `<optgroup>` | Agrupamento de opções |
| `<button>` | Botão |
| `<fieldset>` | Agrupamento de campos |
| `<legend>` | Título de fieldset |
| `<datalist>` | Sugestões para input |
| `<output>` | Resultado de cálculo |
| `<img>` | Imagem |
| `<picture>` | Imagem responsiva |
| `<figure>` | Figura com legenda |
| `<figcaption>` | Legenda de figura |
| `<video>` | Vídeo |
| `<audio>` | Áudio |
| `<track>` | Legenda para vídeo |
| `<iframe>` | Frame incorporado |
| `<svg>` | Gráfico vetorial |
| `<canvas>` | Desenho com JavaScript |
| `<details>` | Conteúdo expansível |
| `<summary>` | Resumo de details |
| `<time>` | Data/hora |
| `<address>` | Informação de contato |
| `<progress>` | Barra de progresso |
| `<meter>` | Medida/gauge |
| `<wbr>` | Quebra de palavra possível |

---

## 💡 Boas Práticas

1. **Sempre use `<label>`** para acessibilidade
2. **Valide no backend** mesmo após validação HTML
3. **Use `required`** para campos obrigatórios
4. **Forneça `alt`** em todas as imagens
5. **Teste responsividade** em múltiplos dispositivos
6. **Use `<picture>`** para imagens em diferentes tamanhos
7. **Estruture formulários** com `<fieldset>`
8. **Forneça múltiplos formatos** de vídeo/áudio
9. **Use `controls`** em vídeos e áudios
10. **Teste acessibilidade** com leitores de tela

---

## 🎓 Parabéns!

Você completou as 4 aulas de HTML! Agora você pode:
- ✅ Criar estruturas semânticas corretas
- ✅ Formatar textos e criar navegação
- ✅ Organizar dados em listas e tabelas
- ✅ Coletar dados com formulários
- ✅ Incorporar mídia em suas páginas

## 🚀 Próximos Passos

Com HTML dominado, você está pronto para:
1. Aprender **CSS** para estilizar suas páginas
2. Aprender **JavaScript** para interatividade
3. Explorar **Responsive Design** para dispositivos móveis
4. Estudar **Acessibilidade Web** (WCAG)
5. Aprender **SEO** para melhorar visibilidade
