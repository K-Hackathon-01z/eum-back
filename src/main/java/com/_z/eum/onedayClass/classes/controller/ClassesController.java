package com._z.eum.onedayClass.classes.controller;

import com._z.eum.onedayClass.classes.dto.request.ClassesRequest;
import com._z.eum.onedayClass.classes.dto.response.ClassesResponse;
import com._z.eum.onedayClass.classes.service.ClassesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "원데이 클래스 API", description = "클래스 등록, 조회, 수정, 삭제 기능 제공")
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    // 전체 클래스 조회
    @GetMapping("/all")
    @Operation(summary = "전체 클래스 조회", description = "저장된 모든 클래스 정보 조회")
    public ResponseEntity<List<ClassesResponse>> getAllClasses() {
        return ResponseEntity.ok(classesService.getAllClasses());
    }

    // 단일 클래스 조회
    @GetMapping("/{id}")
    @Operation(summary = "클래스 단일 조회", description = "클래스 ID로 특정 클래스 정보 조회")
    public ResponseEntity<ClassesResponse> getClassById(@PathVariable Integer id) {
        return ResponseEntity.ok(classesService.getClassById(id));
    }

    // 클래스 생성
    @PostMapping
    @Operation(summary = "클래스 등록", description = "새로운 원데이 클래스를 등록")
    public ResponseEntity<ClassesResponse> createClass(@RequestBody ClassesRequest request) {
        return ResponseEntity.ok(classesService.createClass(request));
    }

    // 클래스 수정
    @PutMapping("/{id}")
    @Operation(summary = "클래스 수정", description = "클래스 ID를 기준으로 클래스 정보를 수정")
    public ResponseEntity<ClassesResponse> updateClass(
            @PathVariable Integer id,
            @RequestBody ClassesRequest request
    ) {
        return ResponseEntity.ok(classesService.updateClass(id, request));
    }

    // 클래스 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "클래스 삭제", description = "클래스 ID를 기준으로 클래스 삭제")
    public ResponseEntity<String> deleteClass(@PathVariable Integer id) {
        classesService.deleteClass(id);
        return ResponseEntity.ok("클래스 삭제 성공했습니다.");
    }
}
