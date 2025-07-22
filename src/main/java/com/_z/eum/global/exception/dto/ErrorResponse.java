package com._z.eum.global.exception.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        HttpStatus httpStatus,
        String message
) { }
