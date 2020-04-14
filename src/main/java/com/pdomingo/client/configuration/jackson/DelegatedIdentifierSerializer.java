package com.pdomingo.client.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pdoming.kernel.core.ddd.DelegatedIdentifier;


import java.io.IOException;

class DelegatedIdentifierSerializer extends StdSerializer<DelegatedIdentifier<?>> {

	protected DelegatedIdentifierSerializer() {
		super(DelegatedIdentifier.class, false);
	}

	@Override
	public void serialize(DelegatedIdentifier<?> id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(id.toString());
	}
}
