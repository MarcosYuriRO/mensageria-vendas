package com.marcos.vendas.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import com.marcos.vendas.domain.TipoPagamento;

public record CupomRequest(@CPF String cpf, BigDecimal valor, TipoPagamento tipoPagamento) {

}
