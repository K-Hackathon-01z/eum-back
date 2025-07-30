package com._z.eum.matching.service;

import com._z.eum.matching.dto.Response.QuestionResponse;
import com._z.eum.matching.entity.CareerTestQuestion;
import com._z.eum.matching.repository.CareerTestRepository;
import com._z.eum.matching.repository.CareerTestResultRepository;
import com._z.eum.matching.dto.Response.MatchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerTestService {

    private final CareerTestRepository careerTestRepository;
    private final CareerTestResultRepository careerTestResultRepository;

    public CareerTestService(CareerTestRepository careerTestRepository, CareerTestResultRepository careerTestResultRepository) {
        this.careerTestResultRepository = careerTestResultRepository;
        this.careerTestRepository = careerTestRepository;
    }


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

    public List<MatchResult> calculateTopSkills(List<Integer> optionIds) {
        return careerTestResultRepository.findSkillScoresByOptionIds(optionIds)
                .stream()
                .limit(10)
                .toList();
    }
}

