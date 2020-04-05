package com.pdomingo.client.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pdomingo.client.domain.model.Client;

import java.io.IOException;

class ClientIdSerializer extends StdSerializer<Client.Id> {

	protected ClientIdSerializer() {
		super(Client.Id.class);
	}

	@Override
	public void serialize(Client.Id id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(id.toString());
	}
}
