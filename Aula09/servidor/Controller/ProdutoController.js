const listaProdutos = [];

class ProdutoControl {
    constructor() {
         
    }

     // Read ALL - Listar todos os objetos
    static listarProdutos() {
        return listaProdutos;
    }

    // Read ONE - Listar um objeto específico
    static listarProduto(idProcurado) {
        return listaProdutos.find(produto => produto.id === idProcurado);
    }

    // Create - Criar um novo objeto
    static criarProduto(produto) {
        listaProdutos.push(produto);
    }

    // Update - Atualizar um objeto existente
    static atualizarProduto(idProcurado, novoProduto) {
        const index = listaProdutos.findIndex(produto => produto.id === idProcurado);
        if (index !== -1) {
            listaProdutos[index] = novoProduto;
            return true;
        }
        return false;
    }

    // Delete - Remover um objeto existente
    static removerProduto(idProcurado) {
        const index = listaProdutos.findIndex(produto => produto.id === idProcurado);
        if (index !== -1) {
            listaProdutos.splice(index, 1);
            return true;
        }
        return false;
    }
}