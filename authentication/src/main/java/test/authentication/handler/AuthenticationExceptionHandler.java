package test.authentication.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("mur.murmur.authentication")
@Slf4j
public class AuthenticationExceptionHandler {
    @ExceptionHandler
    public ProblemDetail handle(Exception e) {
        log.error("handle", e);
        return ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
    }
}
