package br.com.fiap.moneywrench.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.moneywrench.model.Movimentacao;
import br.com.fiap.moneywrench.model.dto.TotalCategoria;
import br.com.fiap.moneywrench.repository.MovimentacaoRepository;

@Service
public class CategoriaService {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    public List<TotalCategoria> getTotalPorCategoria() {

        var movimentacoes = movimentacaoRepository.findAll();

        var totais = movimentacoes.stream()
            .collect(
                Collectors.groupingBy(
                    Movimentacao::getCategoria,
                    Collectors.reducing(
                        BigDecimal.ZERO,
                        Movimentacao::getValor,
                        BigDecimal::add
                    )
                )
            )
            .entrySet()
            .stream()
            .map(e -> new TotalCategoria(e.getKey().getNome(), e.getValue()))
            .collect(Collectors.toList())
        ;

        return totais;

    }
   
    
}
