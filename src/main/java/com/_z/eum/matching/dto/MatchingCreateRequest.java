
package com._z.eum.matching.dto;

import jakarta.validation.constraints.*;

public record MatchingCreateRequest(
        @NotNull
        Integer userId,

        @NotNull
        Integer artisanId,

        @NotBlank @Size(max = 250)
        String message,

        @NotNull
        Boolean isAnonymous
) {}
