package com._z.eum.career.dto.Response;

public record MatchResult(
        int skillId,
        String skillName,
        long totalScore
) {}
