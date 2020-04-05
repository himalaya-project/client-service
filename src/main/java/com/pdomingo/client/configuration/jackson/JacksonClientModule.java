package com.pdomingo.client.configuration.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pdomingo.client.domain.model.Client;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class JacksonClientModule extends SimpleModule {

	public JacksonClientModule() {
		addSerializer(Client.Id.class, new ClientIdSerializer());
		addDeserializer(Client.Id.class, new ClientIdDeserializer());
	}
}
