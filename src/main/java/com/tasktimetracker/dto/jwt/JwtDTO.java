package com.tasktimetracker.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Schema(description = "DTO для возвращения нового JWT")
public record JwtDTO(

        @Schema(description = "User ID (в случае этого тестового - фиктивный)", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID userId,

        @Schema(description = "JWT token", example = "ewogICJhbGciOiJIUzI1NiIKfQ.ewogICJhdXRob3JpdGllcyI6WwogICAgIlJPTEVfQURNSU4iCiAgXSwKICAic3ViIjoidGVzdCIsCiAgImlhdCI6MTcyODQxNjYxMCwKICAiZXhwIjoxNzI4NDIwMjEwCn0.fkxPtTFeyS3uxUIasibEd02H4A9hQqTWw1NIrD7g5C0")
        @NotBlank String jwt) {
}
