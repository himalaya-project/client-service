package com.pdomingo.client.domain.adapter.usecase;

import com.pdomingo.client.domain.event.ClientRegisteredEvent;
import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.client.domain.port.primary.RegisterClientPort;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import com.pdomingo.starter.amqp.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientRegistrationUseCase implements RegisterClientPort {

	private final ClientRepository clientRepository;
	private final EventService eventService;

	public ClientId register(ClientSpec clientSpecification) {
		Objects.requireNonNull(clientSpecification);

		log.info("Proceeding to register client with phone number <{}>", clientSpecification.phoneNumber());

		Client client = clientRepository.create(clientSpecification);

		var clientRegisteredEvent = new ClientRegisteredEvent(
				client.id(),
				client.email(),
				client.phoneNumber(),
				client.shippingAddress()
		);
		eventService.send(clientRegisteredEvent);

		return client.id();
	}
}
