# Backend Node.js com Autenticação JWT

## 📋 Descrição

Este é um servidor backend Node.js construído com **Express.js** que implementa autenticação baseada em **JWT (JSON Web Tokens)** e operações CRUD básicas.

---

## 🚀 Como Funciona

### 1. **Importações e Configuração Inicial**

```javascript
import express from 'express';
import cors from 'cors';

const app = express();
const PORT = 3000;
```

- **express**: Framework web para Node.js que facilita a criação de APIs REST
- **cors**: Middleware para permitir requisições de diferentes origens (cross-origin)
- **PORT**: Define a porta onde o servidor rodará (3000)

### 2. **Middlewares**

```javascript
app.use(express.json());
app.use(cors({
  origin: '*',
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  allowedHeaders: ['Content-Type', 'Authorization'],
}));
```

#### `express.json()`

- Processa requisições com corpo em JSON
- Converte strings JSON em objetos JavaScript

#### `cors()`
Configura o CORS (Cross-Origin Resource Sharing):
- **origin**: `'*'` - permite requisições de qualquer origem
- **methods**: Métodos HTTP permitidos (GET, POST, PUT, DELETE)
- **allowedHeaders**: Headers permitidos incluindo 'Authorization' para tokens JWT

### 3. **Rota de Health Check**

```javascript
app.get('/', async (req, res) => {
  return res.status(200).json({ message: 'API funcionando!' });
});
```

- Rota raiz que verifica se o servidor está funcionando
- Retorna status HTTP 200 (sucesso) com mensagem JSON

### 4. **Inicialização do Servidor**

```javascript
app.listen(PORT, () => {
  console.log(`
  ╔════════════════════════════════════════╗
  ║  🚀 Backend Node.js com JWT            ║
  ║  Servidor rodando em PORT: ${PORT}      ║
  ║  http://localhost:${PORT}               ║
  ║                                        ║
  ║  📝 Documentação: /docs                ║
  ║  🔐 JWT Secret configurado             ║
  ╚════════════════════════════════════════╝
  `);
});
```

- **listen()**: Inicia o servidor na porta 3000
- Exibe mensagem formatada no console quando servidor está pronto

---

## 🔧 Instalação e Execução

### Pré-requisitos
- Node.js (versão 14+)
- npm ou yarn

### Passos

1. **Instalar dependências:**
```bash
npm install
```

2. **Executar o servidor:**
```bash
npm run dev
```
ou
```bash
node server.js
```

3. **Verificar se funciona:**
Abra no navegador ou use curl:
```bash
curl http://localhost:3000
```

Resposta esperada:
```json
{ "message": "API funcionando!" }
```

---

## 📦 Dependências

- **express**: ^4.18.0 - Framework web
- **cors**: ^2.8.5 - Controle de CORS

Adicione ao `package.json`:
```json
{
  "name": "backend-nodejs-jwt",
  "version": "1.0.0",
  "type": "module",
  "scripts": {
    "dev": "node server.js",
    "start": "node server.js"
  },
  "dependencies": {
    "express": "^4.18.0",
    "cors": "^2.8.5"
  }
}
```

---

## 🔐 Próximos Passos

Para expandir este servidor, você precisará adicionar:

1. **Autenticação JWT**
   - Middleware para verificar tokens
   - Rota de login para gerar tokens

2. **Banco de Dados**
   - Conexão com MongoDB ou PostgreSQL
   - Modelos e schemas

3. **Operações CRUD**
   - Rotas POST (criar)
   - Rotas GET (ler)
   - Rotas PUT (atualizar)
   - Rotas DELETE (deletar)

4. **Validação de Dados**
   - Validar entrada dos usuários
   - Tratamento de erros

---

## 🧪 Testando a API

### Com cURL
```bash
curl http://localhost:3000
```

### Com Postman
1. Criar nova requisição GET
2. URL: `http://localhost:3000`
3. Enviar

### Com JavaScript (Fetch)
```javascript
fetch('http://localhost:3000')
  .then(res => res.json())
  .then(data => console.log(data));
```

---

## 📚 Referências

- [Express.js Docs](https://expressjs.com)
- [CORS Middleware](https://www.npmjs.com/package/cors)
- [JWT (JSON Web Tokens)](https://jwt.io)

---

## ✅ Checklist de Implementação

- [ ] Servidor Express rodando na porta 3000
- [ ] CORS configurado
- [ ] Rota GET / funcionando
- [ ] Dependências instaladas
- [ ] Pronto para adicionar autenticação JWT

---

**Criado para:** Aula 07 - ADS4DevWEB (SENAI)  
**Data:** 2026-09-16
