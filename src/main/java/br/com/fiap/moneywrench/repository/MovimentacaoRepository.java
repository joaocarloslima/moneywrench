package br.com.fiap.moneywrench.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.moneywrench.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    // @Query("SELECT m FROM Movimentacao m ORDER BY m.id LIMIT ?2 OFFSET ?1") //JPQL
    // List<Movimentacao> findAll( int offset, int size);

    // @Query("SELECT m FROM Movimentacao m WHERE m.descricao LIKE %?1%")
    Page<Movimentacao> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageRequest);
    
}
