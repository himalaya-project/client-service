package com.pdomingo.client.domain.usecase;

import com.pdomingo.client.domain.exception.ClientNotFoundException;
import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.Status;
import com.pdomingo.client.domain.port.primary.UnregisterClientPort;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import com.pdomingo.client.domain.service.ClientUnregisterOrchestrator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ClientUnregisterUseCasePort implements UnregisterClientPort {

	private final ClientRepository             clientRepository;
	private final ClientUnregisterOrchestrator clientUnregisterOrchestrator;

	public ClientUnregisterUseCasePort(ClientRepository clientRepository, ClientUnregisterOrchestrator clientUnregisterOrchestrator) {
		this.clientRepository = clientRepository;
		this.clientUnregisterOrchestrator = clientUnregisterOrchestrator;
	}

	@Override
	public void unregister(Client.Id clientId) {
		Objects.requireNonNull(clientId);

		Client client = clientRepository.findById(clientId)
				.orElseThrow(ClientNotFoundException::new);

		if (client.status() != Status.UNREGISTERED) {
			return;
		}

		clientUnregisterOrchestrator.unregister(clientId).apply(
				ok -> {
					client.unregister();
					client.eventLog().forEach(System.out::println);
					clientRepository.update(client);
				},
				error -> {
					throw new IllegalStateException(error.asException());
				}
		);
	}
}
