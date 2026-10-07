package com.fatesg.devweb.aula08.loja.categoria;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    public record CategoriaRequest(@NotBlank @Size(max = 80) String nome) { }

    private final CategoriaRepository categorias;

    public CategoriaController(CategoriaRepository categorias) { this.categorias = categorias; }

    @GetMapping
    public List<Categoria> listar() {
        return categorias.findAll();
    }

    @GetMapping("/{id}")
    public Categoria buscar(@PathVariable Long id) {
        return ou404(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria criar(@RequestBody @Valid CategoriaRequest req) {
        return categorias.save(new Categoria(req.nome()));
    }

    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaRequest req) {
        Categoria categoria = ou404(id);
        categoria.setNome(req.nome());
        return categorias.save(categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        categorias.delete(ou404(id));
    }

    private Categoria ou404(Long id) {
        return categorias.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }
}