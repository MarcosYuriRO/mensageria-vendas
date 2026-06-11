package com.marcos.vendas.dto;

import java.math.BigDecimal;

public record CupomResponse(Long id,
		String cpf,
		BigDecimal valor,
		String logradouro,
		String localidade) {

}
