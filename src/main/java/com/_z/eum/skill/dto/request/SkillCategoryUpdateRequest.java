package com._z.eum.skill.dto.request;

public record SkillCategoryUpdateRequest(
        String category,
        String description,
        String imageUrl,
        String careerPath
) { }
