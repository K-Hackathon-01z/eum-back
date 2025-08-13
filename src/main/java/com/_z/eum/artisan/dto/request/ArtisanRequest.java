package com._z.eum.artisan.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "장인 등록 요청 DTO")
public record ArtisanRequest(

        @Schema(description = "기술 카테고리 ID", example = "1")
        Integer skillId,

        @Schema(description = "이메일", example = "artisan@example.com")
        String email,

        @Schema(description = "이름", example = "홍길동")
        String name,

        @Schema(description = "사진 URL")
        String photoUrl,

        @Schema(description = "대표 작품", example = "전통 금속 제품 다수 제작")
        String mainWorks,

        @Schema(description = "약력", example = "30년 경력의 금속 공예 장인")
        String biography
) {
}
