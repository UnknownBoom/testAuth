package test.authentication.adapter.api;


import test.authentication.api.dto.RegistrationRequestDto;

public interface AuthorizationServiceAdapter {
    String create(RegistrationRequestDto registrationRequest);

    void delete(String providerId);
}
