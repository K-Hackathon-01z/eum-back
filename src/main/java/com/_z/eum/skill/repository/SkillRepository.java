package com._z.eum.skill.repository;

import com._z.eum.skill.entity.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillCategory, Integer> {

    //카테고리 오름차순 후 기술 오름차순 조회
    List<SkillCategory> findAllByOrderByCategoryAscNameAsc();

}
