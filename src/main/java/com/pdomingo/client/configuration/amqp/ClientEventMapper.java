package com.pdomingo.client.configuration.amqp;

import com.pdomingo.client.domain.event.ClientDataUpdateEvent;
import com.pdomingo.client.domain.event.ClientRegisteredEvent;
import com.pdomingo.client.domain.event.ClientUnregisteredEvent;
import com.pdomingo.events.client.ClientEvents;
import com.pdomingo.starter.amqp.service.EventMapper;

import java.util.Map;

public class ClientEventMapper extends EventMapper {

    public ClientEventMapper() {
        register(ClientRegisteredEvent.class, ClientEventMapper::mapClientRegisteredEvent);
        register(ClientUnregisteredEvent.class, ClientEventMapper::mapClientUnregisteredEvent);
        register(ClientDataUpdateEvent.class, ClientEventMapper::mapClientDataUpdateEvent);
    }

    private static ClientEvents.ClientRegisteredEvent mapClientRegisteredEvent(ClientRegisteredEvent event) {
        return ClientEvents.ClientRegisteredEvent.newBuilder()
                .setClientId(event.sourceId().toString())
                .setTimestamp(event.timestamp().getEpochSecond())
                .setEmail(event.getEmail().toString())
                .setPhoneNumber(event.getPhoneNumber().rawPhoneNumber())
                .setShippingAddress(event.toString()) // REVIEW THIS
                .build();
    }

    private static ClientEvents.ClientUnregisteredEvent mapClientUnregisteredEvent(ClientUnregisteredEvent event) {
        return ClientEvents.ClientUnregisteredEvent.newBuilder()
                .setClientId(event.sourceId().toString())
                .setTimestamp(event.timestamp().getEpochSecond())
                .build();
    }

    private static final Map<ClientDataUpdateEvent.Field, ClientEvents.ClientDataUpdateEvent.Field> FIELD_MAP = Map.of(
            ClientDataUpdateEvent.Field.ALIAS, ClientEvents.ClientDataUpdateEvent.Field.ALIAS,
            ClientDataUpdateEvent.Field.PHONE_NUMBER, ClientEvents.ClientDataUpdateEvent.Field.PHONE_NUMBER,
            ClientDataUpdateEvent.Field.EMAIL, ClientEvents.ClientDataUpdateEvent.Field.EMAIL,
            ClientDataUpdateEvent.Field.SHIPPING_ADDRESS, ClientEvents.ClientDataUpdateEvent.Field.SHIPPING_ADDRESS
    );

    private static ClientEvents.ClientDataUpdateEvent mapClientDataUpdateEvent(ClientDataUpdateEvent<?> event) {
        return ClientEvents.ClientDataUpdateEvent.newBuilder()
                .setClientId(event.sourceId().toString())
                .setTimestamp(event.timestamp().getEpochSecond())
                .setField(FIELD_MAP.get(event.getUpdatedField()))
                .setValue(event.getUpdatedValue().toString())
                .build();
    }
}
