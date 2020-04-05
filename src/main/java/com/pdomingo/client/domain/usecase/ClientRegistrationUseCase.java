package com.pdomingo.client.domain.usecase;

import com.pdomingo.client.domain.event.ClientRegisteredEvent;
import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.client.domain.port.primary.RegisterClientPort;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ClientRegistrationUseCase implements RegisterClientPort {

	private final ClientRepository clientRepository;

	public ClientRegistrationUseCase(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Client.Id register(ClientSpec clientSpecification) {
		Objects.requireNonNull(clientSpecification);

		Client client = clientRepository.create(clientSpecification);

		var clientRegisteredEvent = new ClientRegisteredEvent(
				client.id(),
				client.email(),
				client.phoneNumber(),
				client.shippingAddress()
		);

		return client.id();
	}
}
