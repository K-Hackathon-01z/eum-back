package com._z.eum.onedayClass.classSchedules.repository;

import com._z.eum.onedayClass.classSchedules.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Integer> {
    List<ClassSchedule> findByClasses_Id(int classId);

    //같은 날짜 같은 시간 중복 확인용
    boolean existsByClasses_IdAndDateAndTimeSlot(int classId, LocalDate date, LocalTime timeSlot);
}
