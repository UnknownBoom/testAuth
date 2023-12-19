package test.authentication.service.impl;


import org.springframework.stereotype.Component;
import test.authentication.model.JwtPair;
import test.authentication.model.JwtUserSession;
import test.authentication.service.api.UserSessionBuilder;

import java.util.UUID;

@Component
public class UserSessionBuilderImpl implements UserSessionBuilder {
    @Override
    public JwtUserSession build(JwtPair jwtPair, UUID userUuid) {
        return JwtUserSession.builder()
                .providerUserId(jwtPair.accessToken().getSubject())
                .userUuid(userUuid)
                .jwtPair(jwtPair)
                .build();
    }
}
