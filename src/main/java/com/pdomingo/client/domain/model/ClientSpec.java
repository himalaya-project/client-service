package com.pdomingo.client.domain.model;

import com.pdoming.kernel.core.vobjects.Address;
import com.pdoming.kernel.core.vobjects.Email;
import com.pdoming.kernel.core.vobjects.PhoneNumber;

import java.time.LocalDate;

public record ClientSpec(
		 String name,
		 String surname1,
		 String surname2,
		 Email email,
		 PhoneNumber phoneNumber,
		 Address shippingAddress,
		 LocalDate dateOfBirth
) {}
