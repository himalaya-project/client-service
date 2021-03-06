package com.pdomingo.client.infrastructure.web.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ClientRegistrationRequest(
		String name,
		String surname1,
		String surname2,
		String email,
		String phoneNumber,
		String shippingAddress,
		String dateOfBirth
) { }
