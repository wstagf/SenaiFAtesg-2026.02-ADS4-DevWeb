package com.fatesg.devweb.aula08.loja.produto;

import java.math.BigDecimal;

public record ProdutoResponse(Long id, String nome, BigDecimal preco,
                              Long categoriaId, String categoriaNome) {

    public static ProdutoResponse de(Produto p) {
        return new ProdutoResponse(p.getId(), p.getNome(), p.getPreco(),
                p.getCategoria().getId(), p.getCategoria().getNome());
    }
}