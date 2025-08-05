package com._z.eum.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record SignupRequest(


        @Email
        @Schema(description = "이메일", example = "example@gmail.com")
         String email,

        @Schema(description = "이름", example = "공일즈")
         String name,

        @Schema(description = "나이", example = "25")
         Integer age,

        @Schema(description = "주소", example = "서울특별시 광진구 능동로 209 (군자동) 세종대학교")
         String address,

        @Schema(description = "비밀번호", example = "EumPassword00")
         String password
) {
}
