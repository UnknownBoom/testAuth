package test.authentication.adapter.impl;

import jakarta.ws.rs.core.Response;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import test.authentication.api.dto.RegistrationRequestDto;
import test.authentication.config.KeycloakProperties;
import test.authentication.adapter.api.AuthorizationServiceAdapter;
import test.authentication.utils.KeycloakUtils;

@RequiredArgsConstructor
@Component
@Slf4j
public class KeycloakAuthorizationServiceAdapter implements AuthorizationServiceAdapter {
    private final Keycloak keycloak;
    private final KeycloakProperties properties;
    private final Converter<RegistrationRequestDto, UserRepresentation> userRepresentationBuilder;

    @Override
    public String create(RegistrationRequestDto request) {
        @Cleanup Response response = keycloak.realm(properties.realm())
                .users()
                .create(userRepresentationBuilder.convert(request));

        if (KeycloakUtils.isSucceeded(response)) {
            return KeycloakUtils.extractUserId(response);
        } else {
            log.error("create: status: {}. body: {}", response.getStatus(), response.readEntity(String.class));
            throw new IllegalStateException("User was not created");
        }
    }

    @Override
    public void delete(String providerId) {
        @Cleanup Response response = keycloak.realm(properties.realm())
                .users()
                .delete(providerId);
        if (!KeycloakUtils.isSucceeded(response)) {
            log.error("delete: status: {}. body: {}", response.getStatus(), response.readEntity(String.class));
            throw new IllegalStateException("User was not deleted");
        }
    }
}
