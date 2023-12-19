package test.authentication.service.impl;


import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Component;
import test.authentication.model.JwtUserSession;
import test.authentication.service.api.UserSessionRepository;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapUserSessionRepository implements UserSessionRepository {
    private final Map<String, JwtUserSession> db = new ConcurrentHashMap<>();

    @Override
    public JwtUserSession save(JwtUserSession userSession) {
        db.put(userSession.getJwtPair().accessToken().getTokenValue(), userSession);
        return userSession;
    }

    @Override
    public JwtUserSession findByAccessToken(String accessToken) {
        JwtUserSession userSession = db.get(accessToken);
        if (Objects.isNull(userSession)) {
            throw new NotFoundException();
        }
        return userSession;
    }

    @Override
    public void delete(JwtUserSession userSession) {
        db.remove(userSession.getJwtPair().accessToken().getTokenValue());
    }
}
