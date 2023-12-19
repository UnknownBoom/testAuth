package test.authentication.service.api;


import test.authentication.model.JwtPair;
import test.authentication.model.JwtUserSession;

import java.util.UUID;

public interface UserSessionBuilder {
    JwtUserSession build(JwtPair jwtPair, UUID userUuid);
}
