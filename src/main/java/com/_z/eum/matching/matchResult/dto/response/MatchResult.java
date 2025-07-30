package com._z.eum.matching.matchResult.dto.response;

public record MatchResult(
        int skillId,
        String skillName,
        long totalScore
) {}
