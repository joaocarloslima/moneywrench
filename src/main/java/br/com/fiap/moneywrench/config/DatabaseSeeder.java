package br.com.fiap.moneywrench.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.moneywrench.model.Categoria;
import br.com.fiap.moneywrench.model.Movimentacao;
import br.com.fiap.moneywrench.repository.CategoriaRepository;
import br.com.fiap.moneywrench.repository.MovimentacaoRepository;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    private Categoria alimentacao = new Categoria(null, "Alimentação", "shopping-cart");
    private Categoria lazer = new Categoria(null, "Lazer", "trophy");
    private Categoria moradia = new Categoria(null, "Moradia", "home");
    private Categoria educacao = new Categoria(null, "Educação", "graduate");

    @Override
    public void run(String... args) throws Exception {

        categoriaRepository.saveAll(
                List.of(alimentacao, lazer, moradia, educacao));

        movimentacaoRepository.saveAll(
                List.of(
                        new Movimentacao()
                                .withDescricao("Aluguel")
                                .withValor(BigDecimal.valueOf(1000))
                                .withData(LocalDate.now())
                                .withTipo("DEBITO")
                                .withCategoria(moradia),

                        new Movimentacao()
                                .withDescricao("Burguer Especial")
                                .withValor(BigDecimal.valueOf(40))
                                .withData(LocalDate.now().minusDays(17))
                                .withTipo("DEBITO")
                                .withCategoria(alimentacao),

                        new Movimentacao()
                                .withDescricao("Faculdade")
                                .withValor(BigDecimal.valueOf(1000))
                                .withData(LocalDate.now().minusDays(14))
                                .withTipo("DEBITO")
                                .withCategoria(educacao),

                        new Movimentacao()
                                .withDescricao("Cinema")
                                .withValor(BigDecimal.valueOf(45))
                                .withData(LocalDate.now().minusDays(16))
                                .withTipo("DEBITO")
                                .withCategoria(lazer),
                        
                        new Movimentacao()
                                .withDescricao("Mc Donalds")
                                .withValor(BigDecimal.valueOf(55))
                                .withData(LocalDate.now().minusDays(15))
                                .withTipo("DEBITO")
                                .withCategoria(alimentacao),

                        new Movimentacao()
                                .withDescricao("internet")
                                .withValor(BigDecimal.valueOf(99))
                                .withData(LocalDate.now().minusDays(10))
                                .withTipo("DEBITO")
                                .withCategoria(moradia)
                                
                )
        );

    }

}
