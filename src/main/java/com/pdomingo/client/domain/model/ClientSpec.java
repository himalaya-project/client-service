package com.pdomingo.client.domain.model;

import com.pdomingo.kernel.core.vobjects.Address;
import com.pdomingo.kernel.core.vobjects.Email;
import com.pdomingo.kernel.core.vobjects.PhoneNumber;

import java.time.LocalDate;

public record ClientSpec(
		 String alias,
		 String name,
		 String surname1,
		 String surname2,
		 Email email,
		 PhoneNumber phoneNumber,
		 Address shippingAddress,
		 LocalDate dateOfBirth
) {}
