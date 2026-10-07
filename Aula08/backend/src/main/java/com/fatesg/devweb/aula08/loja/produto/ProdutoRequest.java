package com.fatesg.devweb.aula08.loja.produto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank @Size(max = 120) String nome,
        @NotNull @Positive BigDecimal preco,
        @NotNull Long categoriaId) { }
