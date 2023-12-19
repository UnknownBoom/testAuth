package test.authentication.adapter.api;


import test.authentication.api.dto.LoginRequestDto;
import test.authentication.model.JwtPair;

public interface JwtAuthorizationServiceTokenManager {
    JwtPair login(LoginRequestDto loginRequestDto);

    JwtPair refresh(String refreshToken);
}
