package br.com.fiap.moneywrench.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneywrench.model.Categoria;

@RestController
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> categorias = new ArrayList<>();

    @GetMapping("/categorias")
    public List<Categoria> index() {
        return categorias;
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        log.info("cadastrando categoria - " + categoria);
        categoria.setId(categorias.size() + 1L);
        categorias.add(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id) {
        log.info("mostrar categoria com id " + id);
        var categoriaEncontrada = categorias
                .stream()
                .filter((categoria) -> categoria.getId().equals(id))
                .findFirst();

        if (categoriaEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoriaEncontrada.get());

    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("apagando categoria com id " + id);
        var categoriaEncontrada = categorias
                .stream()
                .filter((categoria) -> categoria.getId().equals(id))
                .findFirst();
        
        if (categoriaEncontrada.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        categorias.remove(categoriaEncontrada.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("atualizando dados da categoria com id " + id);
        var categoriaEncontrada = categorias
                .stream()
                .filter((c) -> c.getId().equals(id))
                .findFirst();

        if (categoriaEncontrada.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        categorias.remove(categoriaEncontrada.get());
        categoria.setId(id);
        categorias.add(categoria);

        return ResponseEntity.ok(categoria);
    }

}
