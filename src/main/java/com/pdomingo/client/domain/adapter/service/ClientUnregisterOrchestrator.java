package com.pdomingo.client.domain.adapter.service;

import com.pdomingo.client.domain.model.ClientId;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientUnregisterOrchestrator {

	public Try<?> unregister(ClientId clientId) {
		Objects.requireNonNull(clientId);
		// Send ClientUnregisterCommand to the rest of microservices and await responses
		// how? use thread? use saga-framework?
		return Try.success(null);
	}
}
