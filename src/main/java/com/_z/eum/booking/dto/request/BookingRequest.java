package com._z.eum.booking.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "예약 생성 요청 DTO")
public record BookingRequest(

        @Schema(description = "일정 ID", example = "1")
        int scheduleId,

        @Schema(description = "사용자 ID", example = "3")
        int userId
) {}
