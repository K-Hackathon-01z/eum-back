package com._z.eum.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record EmailConfirmRequest(

        @Email
        @Schema(description = "이메일 인증코드 확인용 이메일", example = "example@gmail.com")
        String email,

        @Schema(description = "이메일 인증코드 확인용 코드", example = "XXXXXX")
        String code) {
}
