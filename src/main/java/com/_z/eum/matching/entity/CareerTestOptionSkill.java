package com._z.eum.matching.entity;

import com._z.eum.skill.entity.SkillCategory;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "career_test_option_skill")
@Getter

//질문 선택지와 기술 간 점수 매핑용 엔티티
//내부 로직용
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
