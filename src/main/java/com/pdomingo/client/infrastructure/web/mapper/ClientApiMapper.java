package com.pdomingo.client.infrastructure.web.mapper;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.model.ClientSpec;
import com.pdomingo.client.infrastructure.web.model.ClientRegistrationRequest;
import com.pdoming.kernel.core.vobjects.Address;
import com.pdoming.kernel.core.vobjects.Email;
import com.pdoming.kernel.core.vobjects.PhoneNumber;

import java.time.LocalDate;

public final class ClientApiMapper {

	public static ClientSpec map(ClientRegistrationRequest order) {
		return new ClientSpec(
				order.name(),
				order.surname1(),
				order.surname2(),
				Email.valueOf(order.email()),
				PhoneNumber.valueOf(order.phoneNumber()),
				Address.valueOf(order.shippingAddress()),
				LocalDate.parse(order.dateOfBirth())
		);
	}

	public static ClientId map(String clientIdStr) {
		return new ClientId(clientIdStr);
	}
}
