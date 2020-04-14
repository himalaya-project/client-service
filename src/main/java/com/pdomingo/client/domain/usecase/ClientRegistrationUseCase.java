package com.pdomingo.client.domain.usecase;

import com.pdomingo.client.domain.event.ClientRegisteredEvent;
import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.client.domain.port.primary.RegisterClientPort;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientRegistrationUseCase implements RegisterClientPort {

	private final ClientRepository clientRepository;
	private final AmqpTemplate     eventService;

	public ClientId register(ClientSpec clientSpecification) {
		Objects.requireNonNull(clientSpecification);

		Client client = clientRepository.create(clientSpecification);

		var clientRegisteredEvent = new ClientRegisteredEvent(
				client.id(),
				client.email(),
				client.phoneNumber(),
				client.shippingAddress()
		);

		eventService.convertAndSend(
				"clientExchange",
				"client.registered",
				clientRegisteredEvent
		);

		return client.id();
	}
}
