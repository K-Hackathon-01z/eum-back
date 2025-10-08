
package com._z.eum.matching.repository;

import com._z.eum.matching.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByArtisanIdAndReadFalseOrderByCreatedAtDesc(Integer artisanId);

    Optional<Notification> findByMatchingRequestId(Integer matchingRequestId);

}
