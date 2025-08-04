package com._z.eum.matching.controller;


import com._z.eum.matching.dto.Response.QuestionResponse;
import com._z.eum.matching.service.CareerTestService;
import com._z.eum.matching.dto.Request.MatchRequest;
import com._z.eum.matching.dto.Response.MatchResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matching")
@Tag(name = "매칭 테스트 질문지, 선택지 API", description = "질문지, 선택지 조회 및 선택 결과 반환 기능 제공")
public class CareerTestController {

    private final CareerTestService careerTestService;


    public CareerTestController(CareerTestService careerTestService){
        this.careerTestService = careerTestService;
    }

    //검사 이력 조회
    @GetMapping("/history/{userId}")
    @Operation(summary = "사용자 검사 이력 조회", description = "해당 사용자가 이미 검사를 수행했는지 여부를 반환")
    public ResponseEntity<Boolean> checkHistory(@PathVariable Integer userId) {
        boolean exists = careerTestService.hasTakenTest(userId);
        return ResponseEntity.ok(exists);
    }


    //모든 질문, 선택지 반환
    @Operation(summary = "전체 질문, 선택지 조회", description = "모든 질문지에 대한 선택지를 제공함")
    @GetMapping("/careerTest")
    public ResponseEntity<List<QuestionResponse>> getQuestions() {
        return ResponseEntity.ok(careerTestService.getAllQuestions());
    }

    @PostMapping("/submit/{userId}")
    @Operation(summary = "응답 제출 및 추천 기술 저장", description = "사용자의 선택지 ID 리스트를 기반으로 추천 기술 10개를 저장하고 반환")
    public ResponseEntity<List<MatchResult>> submitTest(@PathVariable Integer userId, @RequestBody MatchRequest request) {
        return ResponseEntity.ok(careerTestService.processCareerTest(userId, request.optionIds()));
    }

    @GetMapping("/recommendations/{userId}")
    @Operation(summary = "저장된 추천 기술 반환", description = "사용자의 추천 기술 리스트 10개를 반환")
    public ResponseEntity<List<MatchResult>> getRecommendations(@PathVariable Integer userId) {
        return ResponseEntity.ok(careerTestService.getSavedRecommendations(userId));
    }


}
