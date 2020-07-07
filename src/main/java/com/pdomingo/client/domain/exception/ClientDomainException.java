package com.pdomingo.client.domain.exception;

import java.util.HashMap;
import java.util.Map;

public class ClientDomainException extends RuntimeException {

    private final Map<String, Object> metadata;

    public ClientDomainException() {
        this.metadata = new HashMap<>(3);
    }

    public ClientDomainException with(String key, Object value) {
        this.metadata.put(key, value);
        return this;
    }
}
