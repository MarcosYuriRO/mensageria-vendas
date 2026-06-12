package com.marcos.vendas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.vendas.domain.Cupom;
import com.marcos.vendas.domain.TipoPagamento;
import com.marcos.vendas.dto.CupomRequest;
import com.marcos.vendas.repository.CupomRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class CupomControllerIT {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	CupomRepository repository;
	
	@Test
	@WithMockUser
	void givenValidCupomRequest_whenRegistrarCupom_thenReturnHttpStatusCode201AndRegistersCupom()
			throws JsonProcessingException, Exception {
		CupomRequest request = new CupomRequest("693.488.350-09", new BigDecimal("74.99"), TipoPagamento.DEBITO, "21820-959");
		
		mvc.perform(post("/cupom")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
				.with(csrf()))
			.andExpect(status().isCreated());
		
		List<Cupom> cupons = repository.findAll();
		Cupom cupomRegistrado = cupons.getFirst();
		
		assertEquals(1, cupons.size());
		assertEquals(request.cpf(), cupomRegistrado.getCpf());
		assertEquals(request.tipoPagamento(), cupomRegistrado.getTipoPagamento());
		assertTrue(request.valor().compareTo(cupomRegistrado.getValor()) == 0);
		assertEquals("Rua Fonseca", cupomRegistrado.getEndereco().getLogradouro());
		assertEquals("Rio de Janeiro", cupomRegistrado.getEndereco().getLocalidade());
	}
	
}
