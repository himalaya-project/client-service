package com.pdomingo.client.domain.port.primary;

import com.pdomingo.client.domain.model.ClientId;
import com.pdomingo.client.domain.model.ClientSpec;

public interface RegisterClientPort {

	ClientId register(ClientSpec specification);
}
