package com.pdomingo.client.domain.event;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.kernel.event.client.ClientDataUpdated;

public class ClientDataUpdateEvent<F> extends ClientDataUpdated<ClientId, F> {
	public ClientDataUpdateEvent(ClientId sourceId, ClientDataUpdated.Field updatedField, F updatedValue) {
		super(sourceId, updatedField, updatedValue);
	}
}
