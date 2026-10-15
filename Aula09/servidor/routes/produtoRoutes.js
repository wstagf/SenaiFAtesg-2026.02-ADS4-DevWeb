
    const express = require('express');
    const router = express.Router();
    const produtoController = require('../Controller/ProdutoController');

    // delegate the route handling to the controller
    // delegando a manipulação da rota para o controller
    // router.get('/', produtoController.listarProdutos);

    // handle GET request
    // manipulando/ CONTROLANDO a requisição GET 
    router.get('/', (req, res) => {
        // res.send('Listando todos os produtos');
        const produtos = produtoController.listarProdutos();
        res.json(produtos);
        //     GET /produtos - Listar todos os produtos
    });

    router.get('/:id', (req, res) => {
        const produto = produtoController.listarProduto(req.params.id);
        res.json(produto);
        //     GET /produtos/:id - Listar um produto pelo ID
    });

    router.post('/', (req, res) => {
        const novoProduto = produtoController.criarProduto(req.body);
        res.status(201).json(novoProduto);
        //     POST /produtos - Criar um novo produto
    });

    router.put('/:id', (req, res) => {
        const produtoAtualizado = produtoController.atualizarProduto(req.params.id, req.body);
        res.json(produtoAtualizado);
        //     PUT /produtos/:id - Atualizar um produto pelo ID
    });

    router.delete('/:id', (req, res) => {
        produtoController.deletarProduto(req.params.id);
        res.status(204).send();
        //     DELETE /produtos/:id - Deletar um produto pelo ID
    });

    module.exports = router;
