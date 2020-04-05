package com.pdomingo.client.configuration.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.pdomingo.client.domain.model.Client;

import java.io.IOException;

class ClientIdDeserializer extends StdDeserializer<Client.Id> {

	protected ClientIdDeserializer() {
		super(Client.Id.class);
	}

	@Override
	public Client.Id deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		return new Client.Id(jsonParser.getValueAsString());
	}
}
