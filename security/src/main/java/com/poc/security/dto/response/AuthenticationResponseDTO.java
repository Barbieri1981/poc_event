package com.poc.security.dto.response;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class AuthenticationResponseDTO {
    private  String accessToken;
}
