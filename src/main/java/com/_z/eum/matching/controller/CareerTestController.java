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

    //모든 질문, 선택지 반환
    @Operation(summary = "전체 질문, 선택지 조회", description = "모든 질문지에 대한 선택지를 제공함")
    @GetMapping("/careerTest")
    public ResponseEntity<List<QuestionResponse>> getQuestions() {
        return ResponseEntity.ok(careerTestService.getAllQuestions());
    }

    // 테스트 결과 10개 리스트 반환
    @Operation(summary = "성향테스트 결과 조회 및 저장", description = "기술 순위 10개와 합산점수 제공함")
    @PostMapping("/matchResult")
    public ResponseEntity<List<MatchResult>> getMatchResult(@RequestBody MatchRequest matchRequest){
        List<MatchResult> results = careerTestService.calculateTopSkills(matchRequest.optionIds());
        return ResponseEntity.ok(results);
    }

}
