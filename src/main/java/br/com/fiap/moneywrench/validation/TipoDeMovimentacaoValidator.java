package br.com.fiap.moneywrench.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoDeMovimentacaoValidator implements ConstraintValidator<TipoDeMovimentacao, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("DEBITO") || value.equals("CREDITO");
    }

}
