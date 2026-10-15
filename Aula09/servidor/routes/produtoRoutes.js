
    const express = require('express');
    const router = express.Router();

    router.get('/', (req, res) => {
        res.send('Listando todos os produtos');
        //     GET /produtos - Listar todos os produtos
    });

    router.get('/:id', (req, res) => {
        res.send('Listando produto pelo ID');
        //     GET /produtos/:id - Listar um produto pelo ID
    });

    router.post('/', (req, res) => {
        res.send('Criando um novo produto');
        //     POST /produtos - Criar um novo produto
    });

    router.put('/:id', (req, res) => {
        res.send('Atualizando produto pelo ID');
        //     PUT /produtos/:id - Atualizar um produto pelo ID
    });

    router.delete('/:id', (req, res) => {
        res.send('Deletando produto pelo ID');
        //     DELETE /produtos/:id - Deletar um produto pelo ID
    });

    module.exports = router;
