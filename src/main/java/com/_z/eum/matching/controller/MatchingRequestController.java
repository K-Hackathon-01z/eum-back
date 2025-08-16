
package com._z.eum.matching.controller;

import com._z.eum.matching.dto.ArtisanMessageResponse;
import com._z.eum.matching.dto.MatchingCreateRequest;
import com._z.eum.matching.service.MatchingRequestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matching-requests")
public class MatchingRequestController {

    private final MatchingRequestService service;

    //쪽지 보내기
    @PostMapping
    @Operation(summary = "장인에게 쪽지 보내기", description = "사용자 -> 장인 : 익명 여부에 따라 사용자 정보 노출 제어")
    public ResponseEntity<Void> create(@Validated @RequestBody MatchingCreateRequest req) {
        Integer id = service.create(req);
        return ResponseEntity.created(URI.create("/api/matching-requests/" + id)).build();
    }

   //쪽지 조회
    @GetMapping("/artisan/{artisanId}")
    @Operation(summary = "장인 받은 쪽지 목록", description = "익명 여부, 메시지, 작성일, 읽음 여부를 조회. 익명 아니면 사용자 이름/이메일/나이 포함")
    public ResponseEntity<List<ArtisanMessageResponse>> list(
            @PathVariable Integer artisanId,
            @RequestParam(defaultValue = "false") boolean unreadOnly
    ) {
        return ResponseEntity.ok(service.listForArtisan(artisanId, unreadOnly));
    }

    // 읽음 처리
    @PatchMapping("/artisan/{artisanId}/{messageId}/read")
    @Operation(summary = "쪽지 읽음 처리", description = "장인이 자신의 쪽지를 읽음 처리")
    public ResponseEntity<Void> markRead(
            @PathVariable Integer artisanId,
            @PathVariable Integer messageId
    ) {
        service.markRead(artisanId, messageId);
        return ResponseEntity.noContent().build();
    }
}
