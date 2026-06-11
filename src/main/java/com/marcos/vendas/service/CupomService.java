package com.marcos.vendas.service;

import com.marcos.vendas.dto.CupomRequest;
import com.marcos.vendas.dto.CupomResponse;

public interface CupomService {
	CupomResponse registrarCupom(CupomRequest response);
}
