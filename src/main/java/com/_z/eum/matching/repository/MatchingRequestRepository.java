
package com._z.eum.matching.repository;

import com._z.eum.matching.entity.MatchingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingRequestRepository extends JpaRepository<MatchingRequest, Integer> {

    List<MatchingRequest> findByArtisanIdOrderByRequestDateDesc(Integer artisanId);

    List<MatchingRequest> findByArtisanIdAndReadFalseOrderByRequestDateDesc(Integer artisanId);
}
