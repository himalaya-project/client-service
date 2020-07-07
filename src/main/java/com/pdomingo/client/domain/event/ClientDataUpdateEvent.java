package com.pdomingo.client.domain.event;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.kernel.core.ddd.BaseDomainEvent;

import java.time.Instant;

public class ClientDataUpdateEvent<T> extends BaseDomainEvent<ClientId> {

	protected Field updatedField;
	protected T updatedValue;

	public enum Field {
		ALIAS,
		EMAIL,
		PHONE_NUMBER,
		SHIPPING_ADDRESS
	}

	public ClientDataUpdateEvent(ClientId sourceId, Field updatedField, T updatedValue) {
		super(sourceId, Instant.now());
		this.updatedField = updatedField;
		this.updatedValue = updatedValue;
	}

	public Field getUpdatedField() {
		return updatedField;
	}

	public T getUpdatedValue() {
		return updatedValue;
	}
}
