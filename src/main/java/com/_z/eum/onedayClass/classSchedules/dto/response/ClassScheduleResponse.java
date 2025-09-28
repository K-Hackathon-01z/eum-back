package com._z.eum.onedayClass.classSchedules.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "클래스 일정 응답 DTO")
public record ClassScheduleResponse(

        @Schema(description = "일정 ID", example = "10")
        int id,

        @Schema(description = "클래스 ID", example = "1")
        int classId,

        @Schema(description = "수업 날짜 (년월일)", example = "2025-10-02")
        LocalDate date,

        @Schema(description = "수업 시간 (시:분)", example = "14:00:00")
        LocalTime timeSlot,

        @Schema(description = "최대 수용 인원", example = "12")
        int capacity,

        @Schema(description = "현재 예약된 인원", example = "5")
        int currentCount
) {}
