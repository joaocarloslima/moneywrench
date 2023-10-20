package br.com.fiap.moneywrench.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.moneywrench.model.Categoria;
import br.com.fiap.moneywrench.model.Movimentacao;
import br.com.fiap.moneywrench.repository.CategoriaRepository;
import br.com.fiap.moneywrench.repository.MovimentacaoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    private Categoria alimentacao = new Categoria(null, "Alimentação", "shopping-cart");
    private Categoria lazer = new Categoria(null, "Lazer", "trophy");
    private Categoria moradia = new Categoria(null, "Moradia", "home");

    @Override
    public void run(String... args) throws Exception {

        categoriaRepository.saveAll(
                List.of(alimentacao, lazer, moradia));

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
                                .withData(LocalDate.now().minusDays(1))
                                .withTipo("DEBITO")
                                .withCategoria(alimentacao))
        );

    }

}
