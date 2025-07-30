package com._z.eum.matching.dto.Response;

public record MatchResult(
        int skillId,
        String skillName,
        long totalScore
) {}
