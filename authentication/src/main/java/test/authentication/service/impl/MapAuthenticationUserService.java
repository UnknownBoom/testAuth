package test.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.authentication.api.dto.RegistrationRequestDto;
import test.authentication.service.api.AuthenticationUserService;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MapAuthenticationUserService implements AuthenticationUserService {
    private final Map<String, UUID> db = new ConcurrentHashMap<>();

    @Override
    public UUID create(RegistrationRequestDto registrationRequest, String providerId) {
        UUID userUuid = UUID.randomUUID();
        db.put(providerId, userUuid);
        return userUuid;
    }

    @Override
    public UUID findByProviderId(String id) {
        try {
            return Objects.requireNonNull(db.get(id));
        } catch (NullPointerException e) {
            throw new IllegalStateException("User not found by provider id: " + id);
        }
    }
}
