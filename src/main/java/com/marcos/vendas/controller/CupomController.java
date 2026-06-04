package com.marcos.vendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.vendas.dto.CupomRequest;
import com.marcos.vendas.service.CupomService;

@RestController
@RequestMapping("cupom")
public class CupomController {
	
	private CupomService service;
	
	public CupomController(CupomService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Void> registrarCupom(@RequestBody CupomRequest request) {
		service.registrarCupom(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
