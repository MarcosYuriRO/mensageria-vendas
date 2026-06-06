package com.marcos.vendas.controller.exception;

import java.time.Instant;

public record ErroResponse(Instant timestamp, Integer status, String erro, String mensagem) {

}
