
package com._z.eum.matching.repository;

import com._z.eum.matching.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByArtisanIdAndReadFalseOrderByCreatedAtDesc(Integer artisanId);
}
