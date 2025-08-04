package com._z.eum.matching.service;

import com._z.eum.matching.dto.Response.QuestionResponse;
import com._z.eum.matching.entity.CareerTestQuestion;
import com._z.eum.matching.entity.CareerTestRecommendation;
import com._z.eum.matching.entity.CareerTestResult;
import com._z.eum.matching.repository.CareerTestRecommendationRepository;
import com._z.eum.matching.repository.CareerTestRepository;
import com._z.eum.matching.repository.CareerTestOptionSkillRepository;
import com._z.eum.matching.dto.Response.MatchResult;
import com._z.eum.matching.repository.CareerTestResultRepository;
import com._z.eum.skill.entity.SkillCategory;
import com._z.eum.skill.repository.SkillRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CareerTestService {

    private final CareerTestRepository careerTestRepository;
    private final CareerTestOptionSkillRepository careerTestOptionSkillRepository;
    private final CareerTestRecommendationRepository careerTestRecommendationRepository;
    private final CareerTestResultRepository careerTestResultRepository;
    private final SkillRepository skillRepository;

    public CareerTestService(CareerTestRepository careerTestRepository,
                             CareerTestOptionSkillRepository careerTestOptionSkillRepository,
                             CareerTestRecommendationRepository careerTestRecommendationRepository,
                             CareerTestResultRepository careerTestResultRepository,
                             SkillRepository skillRepository) {

        this.careerTestOptionSkillRepository = careerTestOptionSkillRepository;
        this.careerTestRepository = careerTestRepository;
        this.careerTestRecommendationRepository = careerTestRecommendationRepository;
        this.careerTestResultRepository = careerTestResultRepository;
        this.skillRepository = skillRepository;
    }

    //검사 이력 조회
    public boolean hasTakenTest(Integer userId) {
        return careerTestResultRepository.existsByUserId(userId);
    }


    //질문지, 선택지 반환
    public List<QuestionResponse> getAllQuestions() {
        List<CareerTestQuestion> questions = careerTestRepository.findAllByOrderByOrderNoAsc();

        return questions.stream()
                .map(q -> {

                    List<QuestionResponse.OptionResponse> optionDtos = q.getOptions().stream()
                            .map(o -> new QuestionResponse.OptionResponse(
                                    o.getId(),
                                    o.getText(),
                                    o.getOrderNo()
                            )).toList();


                    return new QuestionResponse(
                            q.getId(),
                            q.getText(),
                            q.getOrderNo(),
                            optionDtos
                    );
                })
                .toList();
    }

    //신규 테스트 결과 저장 후 기술 리스트 반환
    public List<MatchResult> processCareerTest(Integer userId, List<Integer> optionIds) {
        List<MatchResult> results = careerTestOptionSkillRepository.findSkillScoresByOptionIds(optionIds)
                .stream().limit(10).toList();

        CareerTestResult result = careerTestResultRepository.save(new CareerTestResult(userId));

        int rank = 1;
        for (MatchResult r : results) {
            careerTestRecommendationRepository.save(new CareerTestRecommendation(result, r.skillId(), (int) r.totalScore(), rank++));
        }

        return results;
    }

    //저장된 결과 이력 조회 후 기술 리스트 반환
    public List<MatchResult> getSavedRecommendations(Integer userId) {
        CareerTestResult latestResult = careerTestResultRepository.findTopByUserIdOrderByUpdatedAtDesc(userId)
                .orElseThrow(() -> new NoSuchElementException("검사 결과가 없습니다"));

        return latestResult.getRecommendations().stream()
                .sorted(Comparator.comparingInt(CareerTestRecommendation::getRanking))
                .map(r -> new MatchResult(r.getSkillId(), skillRepository.findById(r.getSkillId()).orElseThrow().getName(),r.getScore()))
                .toList();
    }
}

