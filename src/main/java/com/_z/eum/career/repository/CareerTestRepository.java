package com._z.eum.career.repository;

import com._z.eum.career.entity.CareerTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CareerTestRepository extends JpaRepository<CareerTestQuestion,Integer> {

    //질문지, 선택지 조회
    List<CareerTestQuestion> findAllByOrderByOrderNoAsc();

}
