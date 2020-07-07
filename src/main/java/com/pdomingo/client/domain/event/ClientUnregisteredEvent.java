package com.pdomingo.client.domain.event;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.kernel.core.ddd.BaseDomainEvent;
import com.pdomingo.kernel.event.client.ClientUnregistered;

import java.time.Instant;

public class ClientUnregisteredEvent extends BaseDomainEvent<ClientId> {
	public ClientUnregisteredEvent(ClientId sourceId) {
		super(sourceId, Instant.now());
	}
}
