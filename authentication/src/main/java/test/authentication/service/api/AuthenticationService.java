package test.authentication.service.api;


import test.authentication.api.dto.LoginRequestDto;
import test.authentication.api.dto.LoginResponseDto;

public interface AuthenticationService {

    LoginResponseDto login(LoginRequestDto loginRequest);

    LoginResponseDto refresh(String token);
}
