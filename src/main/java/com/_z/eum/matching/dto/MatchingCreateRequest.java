package com._z.eum.matching.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자가 장인에게 보내는 쪽지 요청 DTO")
public record MatchingCreateRequest(

        @Schema(description = "사용자 ID", example = "1")
        Integer userId,

        @Schema(description = "장인 ID", example = "10")
        Integer artisanId,

        @Schema(description = "쪽지 본문 (최대 250자)", example = "안녕하세요, 작품 관련 상담을 원합니다.")
        String message,

        @Schema(description = "익명 여부", example = "false")
        Boolean isAnonymous
) {}
