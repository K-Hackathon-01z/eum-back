package com._z.eum.booking.repository;


import com._z.eum.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsBySchedule_IdAndUser_Id(int scheduleId, int userId);

}
