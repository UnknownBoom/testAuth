package test.authentication.service.api;


import test.authentication.api.dto.LoginResponseDto;
import test.authentication.api.dto.RegistrationRequestDto;

public interface RegistrationService {
    LoginResponseDto registration(RegistrationRequestDto registrationRequest);
}
