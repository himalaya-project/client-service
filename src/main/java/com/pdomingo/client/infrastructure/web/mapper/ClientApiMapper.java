package com.pdomingo.client.infrastructure.web.mapper;

import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.client.infrastructure.web.model.ClientView;
import com.pdomingo.client.infrastructure.web.model.ClientRegistrationRequest;
import com.pdomingo.kernel.core.vobjects.Address;
import com.pdomingo.kernel.core.vobjects.Email;
import com.pdomingo.kernel.core.vobjects.PhoneNumber;

import java.time.LocalDate;

public final class ClientApiMapper {

	public static ClientSpec map(ClientRegistrationRequest order) {
		return new ClientSpec(
				order.alias(),
				order.name(),
				order.surname1(),
				order.surname2(),
				Email.valueOf(order.email()),
				PhoneNumber.valueOf(order.phoneNumber()),
				Address.builder()
						.withStreet(order.shippingAddress().street())
						.withStreetNumber(order.shippingAddress().streetNumber())
						.withPostalCode(order.shippingAddress().postalCode())
						.withCountry(order.shippingAddress().country())
						.withCity(order.shippingAddress().city())
						.build(),
				LocalDate.parse(order.dateOfBirth())
		);
	}

	public static ClientView map(Client client) {
		return new ClientView(
				client.alias(),
				client.fullName(),
				client.email().toString(),
				client.phoneNumber().rawPhoneNumber(),
				client.shippingAddress().toString(),
				client.status().toString()
		);
	}
}
