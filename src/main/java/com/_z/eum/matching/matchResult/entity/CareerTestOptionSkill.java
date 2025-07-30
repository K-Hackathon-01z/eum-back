package com._z.eum.matching.matchResult.entity;

import com._z.eum.matching.careerTest.entity.CareerTestOption;
import com._z.eum.skill.entity.SkillCategory;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "career_test_option_skill")
@Getter
public class CareerTestOptionSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    // 선택지와 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private CareerTestOption option;

    // 기술과 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillCategory skill;

    private int score;

    protected CareerTestOptionSkill(){}
}
