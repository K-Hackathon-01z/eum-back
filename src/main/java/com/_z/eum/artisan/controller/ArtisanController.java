// src/main/java/com/_z/eum/artisan/controller/ArtisanController.java
package com._z.eum.artisan.controller;

import com._z.eum.artisan.dto.request.ArtisanInfoUpdateRequest;
import com._z.eum.artisan.dto.request.ArtisanRequest;
import com._z.eum.artisan.dto.response.ArtisanResponse;
import com._z.eum.artisan.service.ArtisanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artisans")
@Tag(name = "장인 API", description = "등록, 정보 수정, 삭제 조회 기능 제공")
public class ArtisanController {

    private final ArtisanService artisanService;

    // 등록
    @PostMapping
    @Operation(summary = "장인 회원가입", description = "이메일, 이름, 사진, 작품, 약력을 받아 회원가입 성공여부 반환")
    public ResponseEntity<String> create(@Valid @RequestBody ArtisanRequest request){
        artisanService.create(request);
        return ResponseEntity.ok("회원가입 성공하였습니다.");
    }

    // 정보 수정
    @PutMapping("/{id}")
    @Operation(summary = "장인 정보 변경", description = "사진, 작퓸, 약력를 입력 받아 변경 정보 수정 성공 여부를 반환")
    public ResponseEntity<String> update(@PathVariable Integer id,
                                       @Valid @RequestBody ArtisanInfoUpdateRequest request){
        artisanService.update(id, request);
        return ResponseEntity.ok("정보 변경 성공하였습니다.");
    }

    // 전체 조회
    @GetMapping("/all")
    @Operation(summary = "전체 장인 조회", description = "저장된 전체 장인의 정보 조회")
    public ResponseEntity<List<ArtisanResponse>> getAll(){
        return ResponseEntity.ok(artisanService.getAllArtisan());
    }

    // 아이디로 조회
    @GetMapping("/{id}")
    @Operation(summary = "아이디로 단일 장인 정보 조회", description = "장인 고유 id로 해당 장인의 정보 조회")
    public ResponseEntity<ArtisanResponse> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(artisanService.getArtisanById(id));
    }

    // 아이디로 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "장인 삭제", description = "장인 삭제 기능")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        artisanService.deleteArtisanById(id);
        return ResponseEntity.noContent().build();
    }
}
