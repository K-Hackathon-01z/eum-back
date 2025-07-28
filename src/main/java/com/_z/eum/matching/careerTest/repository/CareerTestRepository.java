package com._z.eum.matching.careerTest.repository;

import com._z.eum.matching.careerTest.entity.CareerTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CareerTestRepository extends JpaRepository<CareerTestQuestion,Integer> {

    List<CareerTestQuestion> findAllByOrderByOrderNoAsc();

}
