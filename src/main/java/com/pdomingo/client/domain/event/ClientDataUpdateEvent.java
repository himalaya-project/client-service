package com.pdomingo.client.domain.event;

import com.pdomingo.kernel.event.client.ClientDataUpdated;
import com.pdomingo.client.domain.model.Client;

public class ClientDataUpdateEvent<F> extends ClientDataUpdated<Client.Id, F> {
	public ClientDataUpdateEvent(Client.Id sourceId, ClientDataUpdated.Field updatedField, F updatedValue) {
		super(sourceId, updatedField, updatedValue);
	}
}
