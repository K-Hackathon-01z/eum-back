package com._z.eum.matching.matchResult.repository;

import com._z.eum.matching.matchResult.dto.response.MatchResult;
import com._z.eum.matching.matchResult.entity.CareerTestOptionSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CareerTestResultRepository extends JpaRepository<CareerTestOptionSkill, Integer> {

    @Query("""
        SELECT new com._z.eum.matching.matchResult.dto.response.MatchResult(
                    s.skill.id,
                    SUM(s.score)
                )
                FROM CareerTestOptionSkill s
                JOIN s.option o
                WHERE o.id IN :optionIds
                GROUP BY s.skill.id
                ORDER BY SUM(s.score) DESC
   
    """)
    List<MatchResult> findSkillScoresByOptionIds(@Param("optionIds") List<Integer> optionIds);
}
