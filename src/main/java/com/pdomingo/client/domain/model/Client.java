package com.pdomingo.client.domain.model;

import com.pdomingo.client.domain.event.ClientDataUpdateEvent;
import com.pdomingo.client.domain.event.ClientUnregisteredEvent;
import com.pdoming.kernel.core.ddd.AggregateRoot;
import com.pdoming.kernel.core.util.MorePreconditions;
import com.pdoming.kernel.core.vobjects.Address;
import com.pdoming.kernel.core.vobjects.Email;
import com.pdoming.kernel.core.vobjects.PhoneNumber;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Objects;

import static com.pdomingo.kernel.event.client.ClientDataUpdated.Field;

public class Client extends AggregateRoot<ClientId> {

	private final ClientId      clientId;
	private       String        name;
	private       String        surname1;
	private       String        surname2;
	private       Email         email;
	private       PhoneNumber   phoneNumber;
	private       Address       shippingAddress;
	private       LocalDate     dateOfBirth;
	private       ZonedDateTime registrationDate;
	private       Status        status;

	public Client(ClientId clientId, String name, String surname1, String surname2, Email email, PhoneNumber phoneNumber,
	              Address shippingAddress, LocalDate dateOfBirth, ZonedDateTime registrationDate, Status status)
	{
		this.clientId = Objects.requireNonNull(clientId, "Field clientId cannot be null");
		this.name = MorePreconditions.checkNotEmptyOrNull(name, "name");
		this.surname1 = MorePreconditions.checkNotEmptyOrNull(surname1, "surname1");
		this.surname2 = MorePreconditions.checkNotEmptyOrNull(surname2, "surname2");
		this.email = Objects.requireNonNull(email, "Field email cannot be null");
		this.phoneNumber = Objects.requireNonNull(phoneNumber, "Field phoneNumber cannot be null");
		this.shippingAddress = Objects.requireNonNull(shippingAddress, "Field shippingAddress cannot be null");
		this.dateOfBirth = Objects.requireNonNull(dateOfBirth, "Field dateOfBirth cannot be null");
		this.registrationDate = Objects.requireNonNull(registrationDate, "Field registrationDate cannot be null");
		this.status = Objects.requireNonNull(status, "Field status cannot be null");
	}

	/*------------------------------------------------------------------*/

	@Override
	public ClientId id() {
		return clientId;
	}

	public String name() {
		return name;
	}

	public Email email() {
		return email;
	}

	public PhoneNumber phoneNumber() {
		return phoneNumber;
	}

	public Address shippingAddress() {
		return shippingAddress;
	}

	public ZonedDateTime registrationDate() {
		return registrationDate;
	}

	public Client changeName(String newName) {
		MorePreconditions.checkNotEmptyOrNull(newName, "newName");
		if (!newName.equals(name)) {
			name = newName;
			eventLog.add(new ClientDataUpdateEvent<>(clientId, Field.NAME, newName));
		}
		return this;
	}

	public Client changeEmail(Email newEmail) {
		Objects.requireNonNull(newEmail);
		if (!newEmail.equals(email)) {
			email = newEmail;
			eventLog.add(new ClientDataUpdateEvent<>(clientId, Field.EMAIL, newEmail));
		}
		return this;
	}

	public Client changePhoneNumber(PhoneNumber newPhoneNumber) {
		Objects.requireNonNull(newPhoneNumber);
		if (!newPhoneNumber.equals(phoneNumber)) {
			phoneNumber = newPhoneNumber;
			eventLog.add(new ClientDataUpdateEvent<>(clientId, Field.PHONE_NUMBER, newPhoneNumber));
		}
		return this;
	}

	public Client changeShippingAddress(Address newShippingAddress) {
		Objects.requireNonNull(newShippingAddress);
		if (!newShippingAddress.equals(shippingAddress)) {
			shippingAddress = newShippingAddress;
			eventLog.add(new ClientDataUpdateEvent<>(clientId, Field.SHIPPING_ADDRESS, newShippingAddress));
		}
		return this;
	}

	public boolean todayIsBirthday() {
		LocalDate today = LocalDate.now();
		return today.getMonthValue() == dateOfBirth.getMonthValue() &&
				today.getDayOfYear() == dateOfBirth.getDayOfYear();
	}

	public boolean isAdult() {
		return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
	}

	public Status status() {
		return status;
	}

	public Client unregister() {
		if (status == Status.UNREGISTERED) {
			throw new IllegalStateException("The client is already unregistered");
		}
		status = Status.UNREGISTERED;
		eventLog.add(new ClientUnregisteredEvent(clientId));
		return this;
	}
}
