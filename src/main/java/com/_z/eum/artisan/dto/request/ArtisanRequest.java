package com._z.eum.artisan.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArtisanRequest(
        @NotNull
        int skillId,

        @NotBlank
        String name,

        String email,

        String photoUrl,

        String mainWorks,
        String biography
) {
}
