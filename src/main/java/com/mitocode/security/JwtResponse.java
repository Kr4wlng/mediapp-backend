package com.mitocode.security;

import com.fasterxml.jackson.annotation.JsonProperty;

// Clase S3
public record JwtResponse(@JsonProperty(value = "acces_token") String accessToken) {
}
