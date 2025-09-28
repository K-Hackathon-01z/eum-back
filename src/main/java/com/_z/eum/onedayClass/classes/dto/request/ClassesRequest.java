package com._z.eum.onedayClass.classes.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "클래스 생성, 수정 요청 DTO")
public record ClassesRequest(
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
