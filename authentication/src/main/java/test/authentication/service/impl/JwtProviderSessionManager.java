package test.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test.authentication.adapter.api.JwtAuthorizationServiceTokenManager;
import test.authentication.api.dto.LoginRequestDto;
import test.authentication.model.JwtPair;
import test.authentication.model.JwtUserSession;
import test.authentication.service.api.AuthenticationUserService;
import test.authentication.service.api.SessionManager;
import test.authentication.service.api.UserSessionBuilder;
import test.authentication.service.api.UserSessionRepository;
import test.authentication.utils.Tokens;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProviderSessionManager implements SessionManager {
    private final JwtAuthorizationServiceTokenManager tokenManager;
    private final AuthenticationUserService userService;
    private final UserSessionRepository repository;
    private final UserSessionBuilder userSessionBuilder;

    @Override
    public JwtUserSession init(LoginRequestDto loginRequest) {
        JwtPair jwtPair = tokenManager.login(loginRequest);
        UUID userUuid = userService.findByProviderId(jwtPair.accessToken().getSubject());
        JwtUserSession userSession = userSessionBuilder.build(jwtPair, userUuid);
        return repository.save(userSession);
    }

    @Override
    public JwtUserSession refresh(String token) {
        String preparedToken = Tokens.cutBearer(token);
        JwtUserSession userSession = repository.findByAccessToken(preparedToken);
        repository.delete(userSession);
        JwtPair refreshed = tokenManager.refresh(userSession.getJwtPair().refreshToken());
        userSession.setJwtPair(refreshed);
        return repository.save(userSession);
    }
}
