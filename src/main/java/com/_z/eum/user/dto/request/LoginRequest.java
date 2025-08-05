package com._z.eum.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record LoginRequest(


        @Email
        @Schema(description = "이메일", example = "example@gmail.com")
         String email,

        @Schema(description = "비밀번호", example = "EumPassword00")
         String password
) {
}
