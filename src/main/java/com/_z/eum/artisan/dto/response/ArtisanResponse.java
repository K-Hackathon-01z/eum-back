package com._z.eum.artisan.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "장인 응답 DTO")
public record ArtisanResponse(
        int id,
        int skillId,
        String name,
        String photoUrl,
        String mainWorks,
        String biography,
        String email
) {
}
