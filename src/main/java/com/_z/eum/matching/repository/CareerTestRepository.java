package com._z.eum.matching.repository;

import com._z.eum.matching.entity.CareerTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CareerTestRepository extends JpaRepository<CareerTestQuestion,Integer> {

    List<CareerTestQuestion> findAllByOrderByOrderNoAsc();

}
