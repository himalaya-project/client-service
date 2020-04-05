package com.pdomingo.client.domain.port.primary;

import com.pdomingo.client.domain.model.Client;
import com.pdomingo.client.domain.model.ClientSpec;

public interface RegisterClientPort {

	Client.Id register(ClientSpec specification);
}
