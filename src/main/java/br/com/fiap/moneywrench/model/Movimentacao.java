package br.com.fiap.moneywrench.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.moneywrench.validation.TipoDeMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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
    
}
