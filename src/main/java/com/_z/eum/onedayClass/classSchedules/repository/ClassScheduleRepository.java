package com._z.eum.onedayClass.classSchedules.repository;

import com._z.eum.onedayClass.classSchedules.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Integer> {
    List<ClassSchedule> findByClasses_Id(int classId);
}
