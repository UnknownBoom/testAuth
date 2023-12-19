package test.authentication.model;

import lombok.Builder;
import org.springframework.security.oauth2.jwt.Jwt;

@Builder
public record JwtPair(Jwt accessToken, String refreshToken) {
}
