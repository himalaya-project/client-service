package com.pdomingo.client.domain.port.primary;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.infrastructure.web.model.ClientView;

public interface FetchClientProfilePort {

	ClientView fetch(ClientId clientId);
}
