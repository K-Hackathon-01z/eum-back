package com._z.eum.onedayClass.classes.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "클래스 정보 응답 DTO")
public record ClassesResponse(
        int id,
        int skillId,
        int artisanId,
        String title,
        String photoUrl,
        String description,
        int price,
        String location,
        int capacity,
        int interestedCount
) {}
