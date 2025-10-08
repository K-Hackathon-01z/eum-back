package com._z.eum.onedayClass.classes.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "클래스 생성, 수정 요청 DTO")
public record ClassesRequest(

        @Schema(description = "기술 카테고리", example = "1")
        int skillId,

        @Schema(description = "기술 장인", example = "1")
        int artisanId,

        @Schema(description = "클래스 이름", example = "목공 기초 — 선반 만들기")
        String title,

        @Schema(description = "클래스 사진(빈 값인 경우 자동 이미지 설정, 이미지 API에서 추가 후 url 첨부할 것 ", example = " ")
        String photoUrl,

        @Schema(description = "클래스 설명", example = "목공 기초 — 선반 만들기는 목공 기초부터 시작하여 선반을 만드는 아주아주 재밌는 원데이 클래스입니다.")
        String description,

        @Schema(description = "클래스 가격", example = "56000")
        int price,

        @Schema(description = "클래스 위치", example = "서울특별시 광진구")
        String location,

        @Schema(description = "클래스 최대 수용 인원", example = "12")
        int capacity
) {}
