package com.pdomingo.client.infrastructure.web.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ClientView(
        String alias,
        String fullName,
        String email,
        String phoneNumber,
        String shippingAddress,
        String status
) {}
