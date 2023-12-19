package test.authentication.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class JwtUserSession {
    private final String providerUserId;
    private final UUID userUuid;
    private JwtPair jwtPair;
}
