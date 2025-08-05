package com._z.eum.career.dto.Response;

import java.util.List;

public record QuestionResponse(
        Integer questionId,
        String text,
        int orderNo,
        List<OptionResponse> options
) {
    public record OptionResponse(
            Integer optionId,
            String text,
            int orderNo
    ) {}
}
