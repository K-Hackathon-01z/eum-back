package com._z.eum.booking.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "예약 응답 DTO")
public record BookingResponse(

        @Schema(description = "예약 ID", example = "100")
        int id,

        @Schema(description = "일정 ID", example = "1")
        int scheduleId,

        @Schema(description = "사용자 ID", example = "10")
        int userId,

        @Schema(description = "예약 날짜", example = "2025-10-03")
        LocalDate date,

        @Schema(description = "예약 시간", example = "14:00:00")
        LocalTime timeSlot,

        @Schema(description = "예약 상태", example = "RESERVED")
        String state
) {}
