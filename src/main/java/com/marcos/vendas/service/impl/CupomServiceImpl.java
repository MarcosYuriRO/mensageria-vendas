package com.marcos.vendas.service.impl;

import org.springframework.stereotype.Service;

import com.marcos.vendas.client.ViaCepClient;
import com.marcos.vendas.domain.Cupom;
import com.marcos.vendas.domain.Endereco;
import com.marcos.vendas.dto.CupomRequest;
import com.marcos.vendas.dto.CupomResponse;
import com.marcos.vendas.dto.EnderecoResponse;
import com.marcos.vendas.repository.CupomRepository;
import com.marcos.vendas.service.CupomService;

@Service
public class CupomServiceImpl implements CupomService {
	
	private CupomRepository repository;
	
	private ViaCepClient viaCepClient;
	
	public CupomServiceImpl(CupomRepository repository, ViaCepClient viacepClient) {
		this.repository = repository;
		this.viaCepClient = viacepClient;
	}

	@Override
	public CupomResponse registrarCupom(CupomRequest request) {
		EnderecoResponse viaCep = viaCepClient.buscarEnderecoPorCep(request.cep());
		
		Endereco endereco = Endereco.builder()
				.cep(viaCep.cep())
				.logradouro(viaCep.logradouro())
				.bairro(viaCep.bairro())
				.localidade(viaCep.localidade())
				.uf(viaCep.uf())
				.build();
		
		Cupom cupom = Cupom.builder()
				.cpf(request.cpf())
				.valor(request.valor())
				.tipoPagamento(request.tipoPagamento())
				.endereco(endereco)
				.build();
		
		repository.save(cupom);
		
		CupomResponse response = new CupomResponse(
				cupom.getId(),
				cupom.getCpf(),
				cupom.getValor(),
				cupom.getEndereco().getLogradouro(),
				cupom.getEndereco().getLocalidade());
		
		return response;
	}

}
