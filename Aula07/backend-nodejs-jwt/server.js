// ========================================
// BACKEND NODEJS COM CRUD E AUTENTICAÇÃO JWT
// ========================================

import express from 'express';
import cors from 'cors';

const app = express();
const PORT = 3000;

// ========================================
// MIDDLEWARE
// ========================================

app.use(express.json());
app.use(cors());

app.get('/', async (req, res) => {
  return res.status(200).json({ message: 'API funcionando!' });
})

let ultimoIDProduto = 1

let listaProdutos = [
  {
    "id": 1,
    "nome": "Celular",
    "modelo": {
      "marca": "Samsung",
      "nomeVersao": "S28mini",
      "partNumber": "xpto123$78090L"
    },
    "preco": 8500.36,
    "bloqueado": false,
    "atributos": [
      "12gb ram", '1tb armazenamento',
      'camera fontral 50mp', 'a prova de agua'
    ],
  }
]

app.get('/produtos', async (req, res) => {
  return res.status(200).json(listaProdutos);
})
// produto by id
app.get('/produto/:id', async (req, res) => {
  const produto = listaProdutos.find(
    p => p.id === parseInt(req.params.id)
  )

  // Essa opcao é para que voce entenda o que as linhas 
  // de 47 a 49 estao fazendo, mas ela é mais lenta
  // const xxxx = null
  // for (let index = 0; index < listaProdutos.length; index++) {
  //   const element = listaProdutos[index];
  //   if(element.id === req.params.id) {
  //     xxxx = element
  //   }
  // }
  if (!produto) {
    return res.status(404).json(
      { message: 'Produto não encontrado' });
  } else {
    return res.status(200).json(produto);
  }
})

app.post('/produto', async (req, res) => {
  const novoProduto = req.body
  novoProduto.id = ultimoIDProduto + 1
  ultimoIDProduto = ultimoIDProduto + 1
  listaProdutos.push(novoProduto)
  return res.status(200).json(novoProduto);
})

app.delete('/produto/:id', async (req, res) => {
  const produto = listaProdutos.find(
    p => p.id === parseInt(req.params.id)
  )

  if (!produto) {
    return res.status(404).json(
      { message: 'Produto não encontrado' });
  } else {
      const id = parseInt(req.params.id)

      listaProdutos = listaProdutos.filter(
        produto => produto.id !== id
      )
    return res.status(200).json({"Sucesso": "O produto foi excluido com sucesso" });
  }
})

app.patch("/produtos/:id", (req, res) => {
  const id = parseInt(req.params.id)

  const produto = listaProdutos.find(p => p.id === id)

  if (!produto) {
    return res.status(404).json({
      mensagem: "Produto não encontrado"
    })
  }

  listaProdutos = listaProdutos.map(produto => {
    if (produto.id === id) {
      return {
        ...produto,
        ...req.body
      }
    }

    return produto
  })

  res.json(listaProdutos.find(p => p.id === id))
})

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

export default app;