package com._z.eum.matching.careerTest.service;

import com._z.eum.matching.careerTest.dto.Response.QuestionResponse;
import com._z.eum.matching.careerTest.entity.CareerTestQuestion;
import com._z.eum.matching.careerTest.repository.CareerTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CareerTestService {

    private final CareerTestRepository careerTestRepository;

    public CareerTestService(CareerTestRepository careerTestRepository){
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
}



