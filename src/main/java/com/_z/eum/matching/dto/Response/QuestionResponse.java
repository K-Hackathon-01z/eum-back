package com._z.eum.matching.dto.Response;

import org.w3c.dom.Text;

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
