package com.marcos.vendas.controller.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroResponse> lidarComArgumentosInvalidos(MethodArgumentNotValidException ex){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		String mensagemLimpa = ex.getBindingResult().getFieldErrors().stream()
	            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	            .collect(Collectors.joining(", "));
		
		ErroResponse erro = new ErroResponse(Instant.now(), status.value(), "Dados de entrada inválidos", mensagemLimpa);
		
		return ResponseEntity.status(status).body(erro);
	}
}
