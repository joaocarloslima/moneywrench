package br.com.fiap.moneywrench.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneywrench.model.Movimentacao;
import br.com.fiap.moneywrench.repository.MovimentacaoRepository;
import br.com.fiap.moneywrench.validation.RestValidationError;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository repository;

    @GetMapping("/movimentacao")
    public Page<Movimentacao> index(
        @PageableDefault(size = 5, sort = "data", direction = Sort.Direction.DESC) Pageable pageRequest, 
        @RequestParam(required = false) String descricao
    ){

        log.info("buscando todas as movimentação");

        if (descricao == null || descricao.isEmpty())
            return repository.findAll(pageRequest);

        return repository.findByDescricaoContainingIgnoreCase(descricao, pageRequest);
    }

    @PostMapping("/movimentacao")
    public ResponseEntity<Object> create(@RequestBody @Valid Movimentacao movimentacao){
        log.info("cadastrando movimentação " + movimentacao);
        repository.save(movimentacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacao);
    }
    
}
