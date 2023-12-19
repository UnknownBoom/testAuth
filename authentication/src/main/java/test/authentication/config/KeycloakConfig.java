package test.authentication.config;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.ClientBuilderWrapper;
import org.keycloak.admin.client.JacksonProvider;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.BasicAuthFilter;
import org.keycloak.admin.client.token.TokenService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeycloakProperties.class)
public class KeycloakConfig {
    @Bean
    public Keycloak keycloakAdminClient(KeycloakProperties properties) {
        return KeycloakBuilder.builder()
                .serverUrl(properties.serverUrl())
                .realm(properties.realm())
                .clientId(properties.clientId())
                .clientSecret(properties.clientSecret())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }

    @Bean
    public TokenService tokenService(KeycloakProperties properties, Client client) {
        WebTarget target = client.target(properties.serverUrl());
        target.register(new BasicAuthFilter(properties.clientId(), properties.clientSecret()));
        return Keycloak.getClientProvider().targetProxy(target, TokenService.class);
    }

    @Bean
    public Client client() {
        ClientBuilder clientBuilder = ClientBuilderWrapper.create(null, false);
        clientBuilder.register(JacksonProvider.class, 100);
        return clientBuilder.build();
    }
}
