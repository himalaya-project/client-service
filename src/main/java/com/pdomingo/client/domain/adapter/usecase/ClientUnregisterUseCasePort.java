package com.pdomingo.client.domain.adapter.usecase;

import com.pdomingo.client.domain.exception.ClientNotFoundException;
import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.model.Status;
import com.pdomingo.client.domain.port.primary.UnregisterClientPort;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import com.pdomingo.client.domain.adapter.service.ClientUnregisterOrchestrator;
import com.pdomingo.starter.amqp.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientUnregisterUseCasePort implements UnregisterClientPort {

	private final ClientRepository             clientRepository;
	private final EventService eventService;
	private final ClientUnregisterOrchestrator clientUnregisterOrchestrator;

	@Override
	public void unregister(ClientId clientId) throws ClientNotFoundException {
		Objects.requireNonNull(clientId);

		log.info("Proceeding to unregister client<{}>", clientId);

		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ClientNotFoundException().with("clientId", clientId));

		if (client.status() != Status.UNREGISTERED) {
			log.warn("Client {} was already unregistered. Skipping unregister process", client);
			return;
		}

		clientUnregisterOrchestrator.unregister(clientId)
				.onSuccess(ok -> {
					client.unregister();
					clientRepository.update(client);
					client.eventLog().forEach(eventService::send);
				})
				.onFailure(error -> {
					log.error("Failed to unregister client<{}>", clientId);
					throw new IllegalStateException(error);
				});
	}
}
