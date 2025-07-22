package com._z.eum.skill.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "기술 카테고리 등록 요청 DTO")
public record SkillCategoryCreateRequest(

        @NotBlank(message = "기술 이름은 필수 입력값입니다.")
        @Schema(description = "기술 이름", example = "금속공예")
        String name,

        @NotBlank(message = "기술 카테고리는 필수 입력값입니다.")
        @Schema(description = "기술 카테고리", example = "공예")
        String category,

        @NotBlank(message = "기술 설명은 필수 입력값입니다.")
        @Schema(description = "기술 설명", example = "금속를 활용한 수공예 기술")
        String description,

        @NotBlank(message = "기술 이미지는 필수 입력값입니다.")
        @Schema(description = "이미지 URL", example = "http://localhost:8080/images/woodcraft.jpg")
        String imageUrl,

        @NotBlank(message = "기술 관련 진로 정보는 필수 입력값입니다.")
        @Schema(description = "관련 진로 정보", example = "도제 → 마이스터 → 자영업")
        String careerPath
) {
}
