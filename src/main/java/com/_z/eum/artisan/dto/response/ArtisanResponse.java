package com._z.eum.artisan.dto.response;

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
