package test.authentication.utils;

import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakUtils {
    public static String extractUserId(Response response) {
        return Optional.ofNullable(response.getHeaderString(HttpHeaders.LOCATION))
                .map(location -> location.substring(location.indexOf("users/") + 6))
                .filter(StringUtils::hasText)
                .orElseThrow(() -> new IllegalStateException("extractUserId: no id found in location header"));
    }

    public static boolean isSucceeded(Response response) {
        Response.Status.Family family = response.getStatusInfo().getFamily();
        return Response.Status.Family.SUCCESSFUL.equals(family);
    }
}
