package com._z.eum.matching.careerTest.controller;


import com._z.eum.matching.careerTest.dto.Response.QuestionResponse;
import com._z.eum.matching.careerTest.service.CareerTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/careerTest")
@Tag(name = "매칭 테스트 질문지, 선택지 API", description = "질문지, 선택지 조회 및 선택 결과 반환 기능 제공")
public class CareerTestController {

    private final CareerTestService careerTestService;

    public CareerTestController(CareerTestService careerTestService){
        this.careerTestService = careerTestService;
    }

    //모든 질문, 선택지 반환
    @Operation(summary = "전체 질문, 선택지 조회", description = "모든 질문지에 대한 선택지를 제공함")
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionResponse>> getQuestions() {
        return ResponseEntity.ok(careerTestService.getAllQuestions());
    }



}
