package com._z.eum.matching.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "장인 또는 사용자가 조회할 때 반환되는 쪽지 응답 DTO")
public record ArtisanMessageResponse(

        @Schema(description = "쪽지 ID", example = "5")
        Integer id,

        @Schema(description = "익명 여부", example = "false")
        Boolean isAnonymous,

        @Schema(description = "쪽지 본문", example = "안녕하세요, 작품 관련 상담을 원합니다.")
        String message,

        @Schema(description = "작성 시간", example = "2025-08-17T01:30:00")
        LocalDateTime requestDate,

        @Schema(description = "읽음 여부", example = "true")
        Boolean read,

        @Schema(description = "읽은 시간", example = "2025-08-17T02:00:00")
        LocalDateTime readAt,

        @Schema(description = "보낸 사용자 이름 (익명이면 null)", example = "김보빈")
        String senderName,

        @Schema(description = "보낸 사용자 이메일 (익명이면 null)", example = "bobin@example.com")
        String senderEmail,

        @Schema(description = "보낸 사용자 나이 (익명이면 null)", example = "25")
        Integer senderAge
) {}
