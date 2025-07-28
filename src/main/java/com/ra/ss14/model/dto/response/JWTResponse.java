package com.ra.ss14.model.dto.response;

import com.ra.ss14.model.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTResponse {
    private String token;
    private String refreshToken;
}
