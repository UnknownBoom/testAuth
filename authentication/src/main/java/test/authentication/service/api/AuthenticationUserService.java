package test.authentication.service.api;


import test.authentication.api.dto.RegistrationRequestDto;

import java.util.UUID;

public interface AuthenticationUserService {
    UUID create(RegistrationRequestDto registrationRequest, String providerId);


    UUID findByProviderId(String id);
}
