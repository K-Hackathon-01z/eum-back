package com._z.eum.onedayClass.classSchedules.service;

import com._z.eum.onedayClass.classSchedules.dto.request.ClassScheduleRequest;
import com._z.eum.onedayClass.classSchedules.dto.response.ClassScheduleResponse;
import com._z.eum.onedayClass.classSchedules.entity.ClassSchedule;
import com._z.eum.onedayClass.classSchedules.repository.ClassScheduleRepository;
import com._z.eum.onedayClass.classes.entity.Classes;
import com._z.eum.onedayClass.classes.repository.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassScheduleService {

    private final ClassScheduleRepository classScheduleRepository;
    private final ClassesRepository classesRepository;

    // 일정 생성
    public ClassScheduleResponse createSchedule(ClassScheduleRequest dto) {
        Classes classes = classesRepository.findById(dto.classId())
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스가 존재하지 않습니다."));

        // 중복 체크
        if (classScheduleRepository.existsByClasses_IdAndDateAndTimeSlot(dto.classId(), dto.date(), dto.timeSlot())) {
            throw new IllegalArgumentException("해당 클래스에 동일한 날짜와 시간의 일정이 이미 존재합니다.");
        }

        ClassSchedule schedule = ClassSchedule.builder()
                .classes(classes)
                .date(dto.date())
                .timeSlot(dto.timeSlot())
                .capacity(dto.capacity())
                .currentCount(dto.currentCount())
                .build();

        return toResponse(classScheduleRepository.save(schedule));
    }

    // 전체 일정 조회
    public List<ClassScheduleResponse> getAllSchedules() {
        return classScheduleRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    // 특정 클래스 일정 전체 조회
    public List<ClassScheduleResponse> getSchedulesByClass(int classId) {
        return classScheduleRepository.findByClasses_Id(classId).stream()
                .map(this::toResponse)
                .toList();
    }

    // 단일 일정 조회
    public ClassScheduleResponse getSchedule(int id) {
        ClassSchedule schedule = classScheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return toResponse(schedule);
    }

    // 일정 수정
    public ClassScheduleResponse updateSchedule(int id, ClassScheduleRequest dto) {
        ClassSchedule schedule = classScheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        Classes classes = classesRepository.findById(dto.classId())
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스가 존재하지 않습니다."));

        // 중복 체크d
        boolean existsDuplicate = classScheduleRepository
                .existsByClasses_IdAndDateAndTimeSlot(dto.classId(), dto.date(), dto.timeSlot());

        if (existsDuplicate && !(schedule.getClasses().getId() == dto.classId()
                && schedule.getDate().equals(dto.date())
                && schedule.getTimeSlot().equals(dto.timeSlot()))) {
            throw new IllegalArgumentException("해당 클래스에 동일한 날짜와 시간의 일정이 이미 존재합니다.");
        }

        schedule.setClasses(classes);
        schedule.setDate(dto.date());
        schedule.setTimeSlot(dto.timeSlot());
        schedule.setCapacity(dto.capacity());
        schedule.setCurrentCount(dto.currentCount());

        return toResponse(classScheduleRepository.save(schedule));
    }

    // 일정 삭제
    public void deleteSchedule(int id) {
        if (!classScheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 일정이 존재하지 않습니다.");
        }
        classScheduleRepository.deleteById(id);
    }

    private ClassScheduleResponse toResponse(ClassSchedule entity) {
        return new ClassScheduleResponse(
                entity.getId(),
                entity.getClasses().getId(),
                entity.getDate(),
                entity.getTimeSlot(),
                entity.getCapacity(),
                entity.getCurrentCount()
        );
    }
}
