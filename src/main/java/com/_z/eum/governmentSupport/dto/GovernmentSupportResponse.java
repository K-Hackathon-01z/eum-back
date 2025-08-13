package com._z.eum.governmentSupport.dto;

import com._z.eum.governmentSupport.entity.GovernmentSupport;

public record GovernmentSupportResponse(
        String title,
        String description,
        String category,
        String targetAge,
        String url
) {
    public GovernmentSupportResponse(GovernmentSupport s) {
        this(s.getTitle(), s.getDescription(), s.getCategory(),
                s.getTargetAge(), s.getUrl());
    }
}
