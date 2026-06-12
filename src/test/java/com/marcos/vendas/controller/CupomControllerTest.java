package com.marcos.vendas.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.vendas.domain.TipoPagamento;
import com.marcos.vendas.dto.CupomRequest;
import com.marcos.vendas.dto.CupomResponse;
import com.marcos.vendas.service.CupomService;

@WebMvcTest
public class CupomControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	CupomService service;
	
	@Test
	@WithMockUser
	void givenValidCupomRequest_whenRegistrarCupom_thenReturnHttpStatusCode201() throws JsonProcessingException, Exception {
		CupomRequest request = new CupomRequest("175.519.390-40", new BigDecimal("10.2"), TipoPagamento.PIX, "21820-959");
		CupomResponse response = new CupomResponse(1L, "175.519.390-40", new BigDecimal("10.2"), 
				"Rua Fonseca" , "Rio de Janeiro");
		
		when(service.registrarCupom(request)).thenReturn(response);
		
		mvc.perform(post("/cupom")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))
				.with(csrf()))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(response.id()))
			.andExpect(jsonPath("$.cpf").value(response.cpf()))
			.andExpect(jsonPath("$.valor").value(response.valor()))
			.andExpect(jsonPath("$.logradouro").value(response.logradouro()))
			.andExpect(jsonPath("$.localidade").value(response.localidade()));
	}
}
