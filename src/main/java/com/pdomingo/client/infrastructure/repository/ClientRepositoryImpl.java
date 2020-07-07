package com.pdomingo.client.infrastructure.repository;

import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.client.domain.model.Status;
import com.pdomingo.client.domain.port.secondary.ClientRepository;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

	private final Map<ClientId, Client> storage;
	private final SecureRandom rnd = new SecureRandom();

	public ClientRepositoryImpl() {
		storage = new HashMap<>();
	}

	@Override
	public Client create(ClientSpec clientSpec) {

		String alias = Optional.ofNullable(clientSpec.alias())
				.orElseGet(this::randomAlias);

		var clientId = new ClientId(UUID.randomUUID());
		var client = new Client(
				clientId,
				alias,
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
	public void delete(ClientId clientId) {
		storage.remove(clientId);
	}

	@Override
	public Optional<Client> findById(ClientId id) {
		return Optional.ofNullable(storage.get(id));
	}

	private String randomAlias() {
		return "anon" + rnd.nextInt(999999999);
	}
}
