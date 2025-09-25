package com._z.eum.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;


@Schema(description = "로그인 요청 DTO")
public record LoginRequest(

        @Email
        @Schema(description = "로그인 시도 이메일", example = "example@gmail.com")
        String email
) {
}
