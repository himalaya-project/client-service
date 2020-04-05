package com.pdomingo.client.domain.event;

import com.pdomingo.client.domain.model.Client;
import com.pdomingo.kernel.event.client.ClientRegistered;
import com.pdomingo.kernel.event.client.ClientUnregistered;

import java.time.Instant;

public class ClientUnregisteredEvent extends ClientUnregistered<Client.Id> {
	public ClientUnregisteredEvent(Client.Id sourceId) {
		super(sourceId, Instant.now());
	}
}
