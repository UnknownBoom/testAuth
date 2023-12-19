package test.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.authentication.api.dto.LoginRequestDto;
import test.authentication.api.dto.LoginResponseDto;
import test.authentication.model.JwtUserSession;
import test.authentication.service.api.AuthenticationService;
import test.authentication.service.api.SessionManager;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final SessionManager manager;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        JwtUserSession userSession = manager.init(loginRequest);
        return prepareResponse(userSession);
    }

    @Override
    public LoginResponseDto refresh(String token) {
        JwtUserSession userSession = manager.refresh(token);
        return prepareResponse(userSession);
    }

    private LoginResponseDto prepareResponse(JwtUserSession userSession) {
        return LoginResponseDto.builder()
                .userUuid(userSession.getUserUuid())
                .accessToken(userSession.getJwtPair().accessToken().getTokenValue())
                .build();
    }
}
