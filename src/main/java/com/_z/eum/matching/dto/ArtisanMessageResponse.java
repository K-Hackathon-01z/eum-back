
package com._z.eum.matching.dto;

import java.time.LocalDateTime;

public record ArtisanMessageResponse(
        Integer id,

        Boolean isAnonymous,

        String message,

        LocalDateTime requestDate,

        Boolean isRead,

        LocalDateTime readAt,

        // 익명이 아닐 때만 채워짐
        String senderName,

        String senderEmail,

        Integer senderAge
) {}
