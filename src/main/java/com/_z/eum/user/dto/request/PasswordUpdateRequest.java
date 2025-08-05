package com._z.eum.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record PasswordUpdateRequest(



        @Email
        @Schema(description = "이메일", example = "example@gmail.com")
         String email,

        @Schema(description = "이전 비밀번호", example = "EumPassword00")
         String oldPassword,

        @Schema(description = "새로운 비밀번호", example = "EumPassword01")
         String newPassword
) {


}
