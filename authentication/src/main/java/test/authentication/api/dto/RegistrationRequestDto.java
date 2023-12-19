package test.authentication.api.dto;

import lombok.Builder;

@Builder
public record RegistrationRequestDto(String username, String password, String email) {
}
