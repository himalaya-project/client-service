package com.pdomingo.client.domain.port.primary;

import com.pdomingo.client.domain.model.Client;

public interface UnregisterClientPort {
	void unregister(Client.Id clientId);
}
