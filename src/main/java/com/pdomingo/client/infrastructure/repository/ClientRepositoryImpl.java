package com.pdomingo.client.infrastructure.repository;

import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.client.domain.model.Status;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

	private final Map<Client.Id, Client> storage;

	public ClientRepositoryImpl() {
		storage = new HashMap<>();
	}

	@Override
	public Client create(ClientSpec clientSpec) {

		var clientId = new Client.Id(UUID.randomUUID());
		var client = new Client(
				clientId,
				clientSpec.name(),
				clientSpec.surname1(),
				clientSpec.surname2(),
				clientSpec.email(),
				clientSpec.phoneNumber(),
				clientSpec.shippingAddress(),
				clientSpec.dateOfBirth(),
				ZonedDateTime.now(),
				Status.ACTIVE
		);

		storage.put(clientId, client);

		return client;
	}

	@Override
	public Client update(Client updatedClient) {
		return storage.put(updatedClient.id(), updatedClient);
	}

	@Override
	public void delete(Client.Id clientId) {
		storage.remove(clientId);
	}

	@Override
	public Optional<Client> findById(Client.Id id) {
		return Optional.ofNullable(storage.get(id));
	}
}
