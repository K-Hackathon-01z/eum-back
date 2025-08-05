package com._z.eum.career.repository;

import com._z.eum.career.entity.CareerTestRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerTestRecommendationRepository extends JpaRepository<CareerTestRecommendation, Integer> {

    // 추천 기술 10개 저장 및 조회
    List<CareerTestRecommendation> findByCareerTestResultIdOrderByRankingAsc(Integer resultId);
}
