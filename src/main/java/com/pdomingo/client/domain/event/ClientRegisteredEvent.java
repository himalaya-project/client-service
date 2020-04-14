package com.pdomingo.client.domain.event;

import com.pdoming.kernel.core.vobjects.Address;
import com.pdoming.kernel.core.vobjects.Email;
import com.pdoming.kernel.core.vobjects.PhoneNumber;
import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.kernel.event.client.ClientRegistered;

import java.time.Instant;

public class ClientRegisteredEvent extends ClientRegistered<ClientId> {
	public ClientRegisteredEvent(ClientId sourceId, Email email, PhoneNumber phoneNumber, Address shippingAddress) {
		super(sourceId, Instant.now(), email, phoneNumber, shippingAddress);
	}
}
