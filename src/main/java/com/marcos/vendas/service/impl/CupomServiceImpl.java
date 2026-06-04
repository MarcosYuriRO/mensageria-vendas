package com.marcos.vendas.service.impl;

import org.springframework.stereotype.Service;

import com.marcos.vendas.domain.Cupom;
import com.marcos.vendas.dto.CupomRequest;
import com.marcos.vendas.repository.CupomRepository;
import com.marcos.vendas.service.CupomService;

@Service
public class CupomServiceImpl implements CupomService {
	
	CupomRepository repository;
	
	public CupomServiceImpl(CupomRepository repository) {
		this.repository = repository;
	}

	@Override
	public void registrarCupom(CupomRequest request) {
		Cupom cupom = Cupom.builder()
				.cpf(request.cpf())
				.valor(request.valor())
				.tipoPagamento(request.tipoPagamento())
				.build();
		repository.save(cupom);
	}

}
