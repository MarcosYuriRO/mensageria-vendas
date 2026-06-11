package com.marcos.vendas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marcos.vendas.dto.EnderecoResponse;

@FeignClient(name = "viaCepClient", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

	@GetMapping("/{cep}/json/")
	EnderecoResponse buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
