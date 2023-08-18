package br.com.fiap.moneywrench.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneywrench.model.Categoria;

@RestController
public class CategoriaController {

    @GetMapping("/categorias")
    public Categoria index(){
        return new Categoria("Educação", "graduate");
    }
    
}
