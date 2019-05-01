package com.gofysh.gofyshbackend.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
}
