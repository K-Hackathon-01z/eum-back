package com._z.eum.matching.matchResult.dto.request;

import java.util.List;

public record MatchRequest(
        List<Integer> optionIds
) {}