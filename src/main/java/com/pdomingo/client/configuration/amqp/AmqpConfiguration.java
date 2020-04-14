package com.pdomingo.client.configuration.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class AmqpConfiguration {

	@Bean
	Binding clientRegisteredBinding() {
		return BindingBuilder.bind(clientRegisteredQueue())
				.to(clientExchange())
				.with("client.registered")
				.noargs();
	}

	@Bean
	Queue clientRegisteredQueue() {
		return new Queue("clientRegisteredQueue");
	}

	@Bean
	Exchange clientExchange() {
		return new TopicExchange("clientExchange");
	}

	@Bean
	MessageConverter jacksonMessageConverter(Jackson2ObjectMapperBuilder builder) {
		return new Jackson2JsonMessageConverter(builder.build());
	}
}
