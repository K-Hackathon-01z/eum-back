package com._z.eum.matching.repository;

import com._z.eum.matching.entity.CareerTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerTestResultRepository extends JpaRepository<CareerTestResult, Integer> {

    //검사 이력 조회
    boolean existsByUserId(Integer userId);

    //가장 최근 검사 결과 조회
    Optional<CareerTestResult> findTopByUserIdOrderByUpdatedAtDesc(Integer userId);
}
