package test.authentication.adapter.impl;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import test.authentication.api.dto.RegistrationRequestDto;

import java.util.List;

@Component
public class KeycloakUserRepresentationBuilder implements Converter<RegistrationRequestDto, UserRepresentation> {

    @Override
    public UserRepresentation convert(RegistrationRequestDto request) {
        UserRepresentation newUser = new UserRepresentation();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setCredentials(List.of(preparePasswordRepresentation(request.password())));
        newUser.setEnabled(true);
        newUser.setEmailVerified(true);
        return newUser;
    }

    private CredentialRepresentation preparePasswordRepresentation(String password) {
        CredentialRepresentation cR = new CredentialRepresentation();
        cR.setTemporary(false);
        cR.setType(CredentialRepresentation.PASSWORD);
        cR.setValue(password);
        return cR;
    }
}
