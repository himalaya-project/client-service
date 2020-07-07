package com.pdomingo.client.domain.event;

import com.pdomingo.kernel.core.ddd.BaseDomainEvent;
import com.pdomingo.kernel.core.vobjects.Address;
import com.pdomingo.kernel.core.vobjects.Email;
import com.pdomingo.kernel.core.vobjects.PhoneNumber;
import com.pdomingo.client.domain.model.ClientId;

import java.time.Instant;

public class ClientRegisteredEvent extends BaseDomainEvent<ClientId> {

	protected Email       email;
	protected PhoneNumber phoneNumber;
	protected Address     shippingAddress;

	public ClientRegisteredEvent(ClientId sourceId, Email email, PhoneNumber phoneNumber, Address shippingAddress) {
		super(sourceId, Instant.now());
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.shippingAddress = shippingAddress;
	}

	public Email getEmail() {
		return email;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}


}
