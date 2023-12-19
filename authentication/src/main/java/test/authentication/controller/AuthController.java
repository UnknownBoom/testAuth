package test.authentication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.authentication.api.dto.LoginRequestDto;
import test.authentication.api.dto.LoginResponseDto;
import test.authentication.api.dto.RegistrationRequestDto;
import test.authentication.service.api.AuthenticationService;
import test.authentication.service.api.RegistrationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public LoginResponseDto registration(@RequestBody RegistrationRequestDto registrationRequest) {
        return registrationService.registration(registrationRequest);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequest) {
        return service.login(loginRequest);
    }

    @PostMapping("/refresh")
    public LoginResponseDto refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return service.refresh(token);
    }
}
