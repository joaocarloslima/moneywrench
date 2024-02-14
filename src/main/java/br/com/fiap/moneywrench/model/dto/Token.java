package br.com.fiap.moneywrench.model.dto;

public record Token(
    String token,
    String type,
    String prefix
) {} 
