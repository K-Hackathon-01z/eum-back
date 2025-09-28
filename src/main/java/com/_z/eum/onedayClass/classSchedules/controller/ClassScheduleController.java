package com._z.eum.onedayClass.classSchedules.controller;

import com._z.eum.onedayClass.classSchedules.dto.request.ClassScheduleRequest;
import com._z.eum.onedayClass.classSchedules.dto.response.ClassScheduleResponse;
import com._z.eum.onedayClass.classSchedules.service.ClassScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-schedules")
@Tag(name = "클래스 일정 API", description = "클래스 일정 등록, 조회, 수정, 삭제 기능 제공")
public class ClassScheduleController {

    private final ClassScheduleService classScheduleService;

    public ClassScheduleController(ClassScheduleService classScheduleService) {
        this.classScheduleService = classScheduleService;
    }

    // 전체 일정 조회
    @GetMapping("/all")
    @Operation(summary = "전체 일정 조회", description = "저장된 모든 클래스 일정 조회")
    public ResponseEntity<List<ClassScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(classScheduleService.getAllSchedules());
    }

    // 특정 클래스 일정 전체 조회
    @GetMapping("/class/{classId}")
    @Operation(summary = "특정 클래스 일정 조회", description = "클래스 ID로 해당 클래스의 모든 일정 조회")
    public ResponseEntity<List<ClassScheduleResponse>> getSchedulesByClass(@PathVariable int classId) {
        return ResponseEntity.ok(classScheduleService.getSchedulesByClass(classId));
    }

    // 단일 일정 조회
    @GetMapping("/{id}")
    @Operation(summary = "일정 단일 조회", description = "일정 ID로 특정 클래스 일정 조회")
    public ResponseEntity<ClassScheduleResponse> getSchedule(@PathVariable int id) {
        return ResponseEntity.ok(classScheduleService.getSchedule(id));
    }

    // 일정 생성
    @PostMapping
    @Operation(summary = "일정 등록", description = "새로운 클래스를 일정으로 등록")
    public ResponseEntity<ClassScheduleResponse> createSchedule(@RequestBody ClassScheduleRequest request) {
        return ResponseEntity.ok(classScheduleService.createSchedule(request));
    }

    // 일정 수정
    @PutMapping("/{id}")
    @Operation(summary = "일정 수정", description = "일정 ID를 기준으로 일정 정보 수정")
    public ResponseEntity<ClassScheduleResponse> updateSchedule(
            @PathVariable int id,
            @RequestBody ClassScheduleRequest request
    ) {
        return ResponseEntity.ok(classScheduleService.updateSchedule(id, request));
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "일정 삭제", description = "일정 ID를 기준으로 일정 삭제")
    public ResponseEntity<String> deleteSchedule(@PathVariable int id) {
        classScheduleService.deleteSchedule(id);
        return ResponseEntity.ok("일정 삭제 성공했습니다.");
    }
}
