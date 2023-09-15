package br.com.fiap.moneywrench.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoDeMovimentacaoValidator.class)
public @interface TipoDeMovimentacao {

    public String message() default "Tipo inválido";

    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
    
}
