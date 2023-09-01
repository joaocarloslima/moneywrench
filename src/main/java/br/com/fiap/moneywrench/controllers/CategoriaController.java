package br.com.fiap.moneywrench.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneywrench.model.Categoria;
import br.com.fiap.moneywrench.repository.CategoriaRepository;

@RestController
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> categorias = new ArrayList<>();

    @Autowired
    CategoriaRepository repository; // IoC - IoD

    @GetMapping("/categorias")
    public List<Categoria> index() {
        return repository.findAll();
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        log.info("cadastrando categoria - " + categoria);
        repository.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id) {
        log.info("mostrar categoria com id " + id);
        return ResponseEntity.ok(getCategoriaById(id));

    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("apagando categoria com id " + id);
        repository.delete(getCategoriaById(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("atualizando dados da categoria com id " + id);
        getCategoriaById(id);
        categoria.setId(id);
        repository.save(categoria);

        return ResponseEntity.ok(categoria);
    }

    private Categoria getCategoriaById(Long id){
       return repository.findById(id).orElseThrow(() -> { 
            return new RuntimeException();
        });
    }

}
