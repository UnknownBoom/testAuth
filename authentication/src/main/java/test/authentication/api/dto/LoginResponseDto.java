package test.authentication.api.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record LoginResponseDto(UUID userUuid, String accessToken) {
}
