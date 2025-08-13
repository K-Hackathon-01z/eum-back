package com._z.eum.governmentSupport.repository;

import com._z.eum.governmentSupport.entity.UserSupportResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserSupportResultRepository extends JpaRepository<UserSupportResult, Integer> {

    List<UserSupportResult> findByUserId(Integer userId);

    Optional<UserSupportResult> findTopByUserIdOrderByRecommendedAtDesc(Integer userId);
}
