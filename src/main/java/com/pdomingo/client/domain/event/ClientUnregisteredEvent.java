package com.pdomingo.client.domain.event;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.kernel.event.client.ClientUnregistered;

import java.time.Instant;

public class ClientUnregisteredEvent extends ClientUnregistered<ClientId> {
	public ClientUnregisteredEvent(ClientId sourceId) {
		super(sourceId, Instant.now());
	}
}
