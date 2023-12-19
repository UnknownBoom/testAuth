package test.authentication.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tokens {
    public static String cutBearer(String token) {
        if (Objects.isNull(token)) {
            return null;
        }

        return token.replace("Bearer ", "");
    }
}
