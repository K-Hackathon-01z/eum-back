package com._z.eum.onedayClass.classSchedules.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "클래스 일정 생성/수정 요청 DTO")
public record ClassScheduleRequest(

        @Schema(description = "클래스 ID", example = "1")
        int classId,

        @Schema(description = "수업 날짜 (년월일)", example = "2025-10-02")
        LocalDate date,

        @Schema(description = "수업 시간 (시:분)", example = "14:00:00")
        LocalTime timeSlot,

        @Schema(description = "최대 수용 인원", example = "12")
        int capacity,

        @Schema(description = "현재 예약된 인원", example = "0")
        int currentCount
) {}
