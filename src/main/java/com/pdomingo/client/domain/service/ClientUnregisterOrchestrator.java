package com.pdomingo.client.domain.service;

import com.pdoming.kernel.core.functional.Result;
import com.pdomingo.client.domain.model.ClientId;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientUnregisterOrchestrator {

	public Result<?> unregister(ClientId clientId) {
		Objects.requireNonNull(clientId);
		// Send ClientUnregisterCommand to the rest of microservices and await responses
		// how? use thread? use saga-framework?
		return Result.ok();
	}
}
