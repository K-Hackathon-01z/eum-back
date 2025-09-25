package com._z.eum.user.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


@Schema(description = "사용자 정보 변경 요청 DTO")
public record UserInfoUpdateRequest(

        @Schema(description = "변경할 이름", example = "김보바")
        String name,

        @Schema(description = "변경할 나이", example = "26")
        int age,

        @Schema(description = "변경할 성별", example = "male")
        String gender,

        @Schema(description = "변경할 주소", example = "경기도 의정부시")
        String address)
{}