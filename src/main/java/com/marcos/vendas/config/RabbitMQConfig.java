package com.marcos.vendas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_ESTOQUE = "vendas.cupom.criado.estoque";
    
    public static final String EXCHANGE_CUPOM = "vendas.cupom.criado";

    @Bean
    public Queue filaEstoque() {
        return new Queue(FILA_ESTOQUE, true); 
    }

    @Bean
    public FanoutExchange exchangeCupom() {
        return new FanoutExchange(EXCHANGE_CUPOM); 
    }

    @Bean
    public Binding bindingEstoque() {
        return BindingBuilder.bind(filaEstoque()).to(exchangeCupom());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}