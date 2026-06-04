package com.marcos.vendas.domain;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cupons")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cupom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@CPF
	private String cpf;
	
	private BigDecimal valor;
	
	@Column(name = "tipo_pagamento")
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
}
