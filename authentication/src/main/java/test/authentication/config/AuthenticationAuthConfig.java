package test.authentication.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({AuthenticationConfig.class, KeycloakConfig.class})
public class AuthenticationAuthConfig {

}
