package com.pdomingo.client.infrastructure.web.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AddressDTO(
        String street,
        String streetNumber,
        String city,
        String postalCode,
        String country
) {}
