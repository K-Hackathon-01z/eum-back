
package com._z.eum.global.controller;

import com._z.eum.global.s3.S3UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images/upload")
@Tag(name = "이미지 업로드 API", description = "S3에 이미지 업로드 후 URL 반환 (도메인별 폴더 구분)")
@RequiredArgsConstructor
public class ImageController {

    private final S3UploadService s3UploadService;

    // Artisan 이미지 업로드
    @PostMapping(value = "/artisan", consumes = {"multipart/form-data"})
    @Operation(summary = "장인 이미지 업로드", description = "S3 artisans 폴더에 이미지 업로드 후 URL 반환")
    public ResponseEntity<String> uploadArtisan(@RequestParam("file") MultipartFile file) throws IOException {
        String url = s3UploadService.uploadFile("artisans", file);
        return ResponseEntity.ok(url);
    }

    // Class 이미지 업로드
    @PostMapping(value = "/class", consumes = {"multipart/form-data"})
    @Operation(summary = "클래스 이미지 업로드", description = "S3 classes 폴더에 이미지 업로드 후 URL 반환")
    public ResponseEntity<String> uploadClass(@RequestParam("file") MultipartFile file) throws IOException {
        String url = s3UploadService.uploadFile("classes", file);
        return ResponseEntity.ok(url);
    }

    // Skill 이미지 업로드
    @PostMapping(value = "/skill", consumes = {"multipart/form-data"})
    @Operation(summary = "기술 이미지 업로드", description = "S3 skills 폴더에 이미지 업로드 후 URL 반환")
    public ResponseEntity<String> uploadSkill(@RequestParam("file") MultipartFile file) throws IOException {
        String url = s3UploadService.uploadFile("skills", file);
        return ResponseEntity.ok(url);
    }

    // Temp 테스트용 업로드
    @PostMapping(value = "/temp", consumes = {"multipart/form-data"})
    @Operation(summary = "테스트용 이미지 업로드", description = "S3 temp 폴더에 이미지 업로드 후 URL 반환 ")
    public ResponseEntity<String> uploadTemp(@RequestParam("file") MultipartFile file) throws IOException {
        String url = s3UploadService.uploadFile("temp", file);
        return ResponseEntity.ok(url);
    }
}
