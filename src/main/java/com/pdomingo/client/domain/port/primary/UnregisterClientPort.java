package com.pdomingo.client.domain.port.primary;

import com.pdomingo.client.domain.model.ClientId;

public interface UnregisterClientPort {
	void unregister(ClientId clientId);
}
