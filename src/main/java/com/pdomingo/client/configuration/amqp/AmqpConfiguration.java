package com.pdomingo.client.configuration.amqp;

import com.pdomingo.client.domain.event.ClientRegisteredEvent;
import com.pdomingo.starter.amqp.service.AmqpRoutingConfiguration;
import com.pdomingo.starter.amqp.service.EventMapper;
import com.pdomingo.starter.amqp.service.Route;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	AmqpRoutingConfiguration routingConfiguration() {

		Binding clientRegisteredBinding = clientRegisteredBinding();

		return AmqpRoutingConfiguration.builder()
				.newRoute(ClientRegisteredEvent.class, Route.from(clientRegisteredBinding.getExchange(), clientRegisteredBinding.getRoutingKey()))
				.build();
	}

	@Bean
	EventMapper eventMapper() {
		return new ClientEventMapper();
	}
}
