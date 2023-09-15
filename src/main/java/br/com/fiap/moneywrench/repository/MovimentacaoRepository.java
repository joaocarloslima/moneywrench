package br.com.fiap.moneywrench.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.moneywrench.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    
}
