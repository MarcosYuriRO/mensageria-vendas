package com.marcos.vendas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.vendas.dto.CupomRequest;
import com.marcos.vendas.dto.CupomResponse;
import com.marcos.vendas.service.CupomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cupom")
public class CupomController {
	
	private CupomService service;
	
	public CupomController(CupomService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<CupomResponse> registrarCupom(@RequestBody @Valid CupomRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarCupom(request));
	}
}
