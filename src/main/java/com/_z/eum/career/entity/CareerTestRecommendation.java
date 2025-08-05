package com._z.eum.career.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "career_test_recommendation")
public class CareerTestRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int score;

    private int ranking;

    @Column(name = "skill_id", nullable = false)
    private int skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_test_result_id", nullable = false)
    private CareerTestResult careerTestResult;

    public CareerTestRecommendation(CareerTestResult result, int skillId, int score, int ranking) {
        this.careerTestResult = result;
        this.skillId = skillId;
        this.score = score;
        this.ranking = ranking;
    }

}
