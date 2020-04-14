package com.pdomingo.client.infrastructure.web;

import com.pdomingo.client.domain.port.primary.RegisterClientPort;
import com.pdomingo.client.domain.port.primary.UnregisterClientPort;
import com.pdomingo.client.infrastructure.web.mapper.ClientApiMapper;
import com.pdomingo.client.infrastructure.web.model.ClientRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(Endpoints.CLIENT_BASE_PATH)
@RequiredArgsConstructor
public class ClientApi {

	private final RegisterClientPort   registerClientPort;
	private final UnregisterClientPort unregisterClientPort;

	@PostMapping
	public ResponseEntity<String> registerClient(
			@RequestBody ClientRegistrationRequest order
	) {
		var clientSpec = ClientApiMapper.map(order);
		var clientId = registerClientPort.register(clientSpec);
		return ResponseEntity.created(URI.create(clientId.toString())).build();
	}

	@DeleteMapping(Endpoints.DELETE_CLIENT)
	public ResponseEntity<String> unregisterClient(
			@PathVariable("clientId") String clientIdStr
	) {
		var clientId = ClientApiMapper.map(clientIdStr);
		unregisterClientPort.unregister(clientId);

		return ResponseEntity.ok().build();
	}
}
