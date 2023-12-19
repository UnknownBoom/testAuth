package test.authentication.service.api;


import test.authentication.model.JwtUserSession;

public interface UserSessionRepository {
    JwtUserSession save(JwtUserSession userSession);

    JwtUserSession findByAccessToken(String accessToken);

    void delete(JwtUserSession userSession);
}
