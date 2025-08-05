package com._z.eum.career.repository;

import com._z.eum.career.dto.Response.MatchResult;
import com._z.eum.career.entity.CareerTestOptionSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerTestOptionSkillRepository extends JpaRepository<CareerTestOptionSkill, Integer> {

    @Query("""
        SELECT new com._z.eum.matching.dto.Response.MatchResult(
                    s.skill.id,
                    s.skill.name,
                    SUM(s.score)
                )
                FROM CareerTestOptionSkill s
                JOIN s.option o
                WHERE o.id IN :optionIds
                GROUP BY s.skill.id, s.skill.name
                ORDER BY SUM(s.score) DESC
   
    """)
    List<MatchResult> findSkillScoresByOptionIds(@Param("optionIds") List<Integer> optionIds);
}
