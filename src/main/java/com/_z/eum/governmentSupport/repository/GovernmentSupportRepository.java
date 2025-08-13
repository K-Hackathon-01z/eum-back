package com._z.eum.governmentSupport.repository;

import com._z.eum.governmentSupport.entity.GovernmentSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GovernmentSupportRepository extends JpaRepository<GovernmentSupport, Integer> {

    Optional<GovernmentSupport> findBySourceApi(String sourceApi);
}
