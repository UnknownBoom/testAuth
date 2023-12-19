package test.authentication.adapter.impl;

import jakarta.ws.rs.core.Form;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.token.TokenService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import test.authentication.adapter.api.JwtAuthorizationServiceTokenManager;
import test.authentication.api.dto.LoginRequestDto;
import test.authentication.config.KeycloakProperties;
import test.authentication.model.JwtPair;

@RequiredArgsConstructor
@Component
public class KeycloakTokenManagerImpl implements JwtAuthorizationServiceTokenManager {
    private final TokenService tokenService;
    private final KeycloakProperties properties;
    private final JwtDecoder jwtDecoder;

    @Override
    public JwtPair login(LoginRequestDto loginRequest) {
        Form loginForm = new Form()
                .param(OAuth2Constants.GRANT_TYPE, OAuth2Constants.PASSWORD)
                .param(OAuth2Constants.USERNAME, loginRequest.username())
                .param(OAuth2Constants.PASSWORD, loginRequest.password());
        AccessTokenResponse accessTokenResponse = tokenService.grantToken(properties.realm(), loginForm.asMap());
        return preparePair(accessTokenResponse);
    }

    @Override
    public JwtPair refresh(String refreshToken) {
        Form form = new Form().param(OAuth2Constants.GRANT_TYPE, OAuth2Constants.REFRESH_TOKEN)
                .param(OAuth2Constants.REFRESH_TOKEN, refreshToken);
        AccessTokenResponse accessTokenResponse = tokenService.refreshToken(properties.realm(), form.asMap());
        return preparePair(accessTokenResponse);
    }

    private JwtPair preparePair(AccessTokenResponse accessTokenResponse) {
        return new JwtPair(
                jwtDecoder.decode(accessTokenResponse.getToken()),
                accessTokenResponse.getRefreshToken()
        );
    }
}
