package com._z.eum.matching.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record MatchRequest(

        @NotBlank(message = "사용자 응답값 5개는 필수 입력값입니다.")
        @Schema(description = "질문지 5개에 대한 사용자 응답값(응답값 ID)", example = " [1, 4, 9, 11, 16]")
        List<Integer> optionIds
) {}