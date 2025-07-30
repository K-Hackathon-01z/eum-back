package com._z.eum.matching.matchResult.controller;


import com._z.eum.matching.matchResult.dto.request.MatchRequest;
import com._z.eum.matching.matchResult.dto.response.MatchResult;
import com._z.eum.matching.matchResult.service.CareerTestResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/matching/matchResult")
public class CareerTestResultController {

    private final CareerTestResultService careerTestResultService;

    public CareerTestResultController(CareerTestResultService careerTestResultService){
        this.careerTestResultService = careerTestResultService;
    }

    // 테스트 결과 10개 리스트 반환
    @PostMapping()
    public ResponseEntity<List<MatchResult>> getMatchResult(@RequestBody MatchRequest matchRequest){
        List<MatchResult> results = careerTestResultService.calculateTopSkills(matchRequest.optionIds());
        return ResponseEntity.ok(results);
    }


}
