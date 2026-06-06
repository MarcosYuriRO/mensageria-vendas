package com.marcos.vendas.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import com.marcos.vendas.domain.TipoPagamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CupomRequest(@CPF String cpf, @NotNull @Positive BigDecimal valor, @NotNull TipoPagamento tipoPagamento) {

}
