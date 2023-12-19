package test.authentication.model;

import lombok.Builder;

@Builder
public record JwtUserSessionDto(JwtUserSession userSession, String userUuid) {
}
