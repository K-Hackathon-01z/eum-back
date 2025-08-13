package com._z.eum.governmentSupport.dto;

public record YouthPolicyResponse(
        String sourceApi,
        String title,
        String description,
        String category,
        String targetAge,
        String targetLocation,
        String url
) {
}
