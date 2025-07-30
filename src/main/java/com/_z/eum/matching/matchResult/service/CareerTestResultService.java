package com._z.eum.matching.matchResult.service;

import com._z.eum.matching.matchResult.dto.response.MatchResult;
import com._z.eum.matching.matchResult.repository.CareerTestResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerTestResultService {

    private final CareerTestResultRepository careerTestResultRepository;

    public CareerTestResultService(CareerTestResultRepository careerTestResultRepository){
        this.careerTestResultRepository = careerTestResultRepository;
    }

    public List<MatchResult> calculateTopSkills(List<Integer> optionIds) {
        return careerTestResultRepository.findSkillScoresByOptionIds(optionIds)
                .stream()
                .limit(10)
                .toList();
    }
}
