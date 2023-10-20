package br.com.fiap.moneywrench.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.fiap.moneywrench.validation.TipoDeMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movimentacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min = 5, message = "a descrição deve ter pelo menos 5 caracteres")
    private String descricao;

    @PastOrPresent
    private LocalDate data;

    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;

    @TipoDeMovimentacao(message = "Tipo de movimentação inválido, deve ser DEBITO e CREDITO.")
    private String tipo;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    public Movimentacao withId(Long id) {
        this.id = id;
        return this;
    }

    public Movimentacao withDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Movimentacao withValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Movimentacao withData(LocalDate data) {
        this.data = data;
        return this;
    }
    
    public Movimentacao withTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public Movimentacao withCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }
}
