package test.authentication.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import test.authentication.api.dto.LoginRequestDto;
import test.authentication.api.dto.LoginResponseDto;
import test.authentication.api.dto.RegistrationRequestDto;
import test.authentication.adapter.api.AuthorizationServiceAdapter;
import test.authentication.service.api.AuthenticationService;
import test.authentication.service.api.AuthenticationUserService;
import test.authentication.service.api.RegistrationService;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AuthenticationService authenticationService;
    private final AuthenticationUserService userService;
    private final AuthorizationServiceAdapter authorizationServiceAdapter;

    @Override
    public LoginResponseDto registration(RegistrationRequestDto registrationRequest) {
        String providerId = authorizationServiceAdapter.create(registrationRequest);
        try {
            userService.create(registrationRequest, providerId);
        } catch (Exception e) {
            authorizationServiceAdapter.delete(providerId);
            throw e;
        }
        return authenticationService.login(LoginRequestDto.builder()
                .username(registrationRequest.username())
                .password(registrationRequest.password())
                .build());
    }
}
