package com._z.eum.governmentSupport.controller;


import com._z.eum.governmentSupport.dto.GovernmentSupportResponse;
import com._z.eum.governmentSupport.service.GovernmentSupportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/supports")
@Tag(name = "정부 혜택 API", description = "일주일마다 갱신하여 지원 가능한 정부 혜택 제공")
public class GovernmentSupportController {

    private final GovernmentSupportService governmentSupportService;

    public GovernmentSupportController(GovernmentSupportService governmentSupportService){
        this.governmentSupportService = governmentSupportService;
    }


    @GetMapping("/recommend/{userId}")
    @Operation(summary = "정부혜택 조회", description = "사용자 id로 지원 가능한 정부혜택의 제목, 설명, 타겟 나이, 카테고리 제공")
    public ResponseEntity<List<GovernmentSupportResponse>> recommend(@PathVariable Integer userId) {
        List<GovernmentSupportResponse> result = governmentSupportService.recommendSupports(userId);
        return ResponseEntity.ok(result);
    }
}
