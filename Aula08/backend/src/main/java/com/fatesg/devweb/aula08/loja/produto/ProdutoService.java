package com.fatesg.devweb.aula08.loja.produto;

import java.util.List;

import com.fatesg.devweb.aula08.loja.categoria.Categoria;
import com.fatesg.devweb.aula08.loja.categoria.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ProdutoService {

    private final ProdutoRepository produtos;
    private final CategoriaRepository categorias;

    public ProdutoService(ProdutoRepository produtos, CategoriaRepository categorias) {
        this.produtos = produtos;
        this.categorias = categorias;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listar(Long categoriaId) {
        List<Produto> lista = (categoriaId == null)
                ? produtos.findAll()
                : produtos.findByCategoriaId(categoriaId);
        return lista.stream().map(ProdutoResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponse buscar(Long id) {
        return ProdutoResponse.de(produtoOu404(id));
    }

    public ProdutoResponse criar(ProdutoRequest req) {
        Categoria categoria = categoriaOu400(req.categoriaId());
        Produto salvo = produtos.save(new Produto(req.nome(), req.preco(), categoria));
        return ProdutoResponse.de(salvo);
    }

    public ProdutoResponse atualizar(Long id, ProdutoRequest req) {
        Produto produto = produtoOu404(id);
        produto.atualizar(req.nome(), req.preco(), categoriaOu400(req.categoriaId()));
        return ProdutoResponse.de(produto);
    }

    public void excluir(Long id) {
        produtos.delete(produtoOu404(id));
    }

    private Produto produtoOu404(Long id) {
        return produtos.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    private Categoria categoriaOu400(Long id) {
        return categorias.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente"));
    }
}