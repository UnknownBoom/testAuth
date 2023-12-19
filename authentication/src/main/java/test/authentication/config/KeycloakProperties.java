package test.authentication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("keycloak")
public record KeycloakProperties(String serverUrl, String realm, String clientId, String clientSecret) {
}
