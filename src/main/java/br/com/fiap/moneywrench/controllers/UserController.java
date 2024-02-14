package br.com.fiap.moneywrench.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneywrench.model.dto.Token;
import br.com.fiap.moneywrench.service.TokenService;

@RestController
public class UserController {

    @Autowired
    TokenService service;
    
    @PostMapping("/login")
    public ResponseEntity<Token> login(){
        return ResponseEntity.ok( service.generateToken("joao@fiap.com.br") );
    }

}
