package com._z.eum.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record EmailRequest(

        @Email
        @Schema(description = "인증코드를 전송할 이메일", example = "example@gmail.com")
        String email
) { }
