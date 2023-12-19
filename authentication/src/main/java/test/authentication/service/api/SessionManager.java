package test.authentication.service.api;


import test.authentication.api.dto.LoginRequestDto;
import test.authentication.model.JwtUserSession;
import test.authentication.model.JwtUserSessionDto;

public interface SessionManager {
    JwtUserSession init(LoginRequestDto sessionInitRequest);

    JwtUserSession refresh(String token);
}
