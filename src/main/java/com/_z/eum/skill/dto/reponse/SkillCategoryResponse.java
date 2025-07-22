package com._z.eum.skill.dto.reponse;

public record SkillCategoryResponse(
        int id,
        String name,
        String category,
        String description,
        String image_url,
        String career_path
) {}
