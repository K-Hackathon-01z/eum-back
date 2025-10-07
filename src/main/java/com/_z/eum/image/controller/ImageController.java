package com._z.eum.image.controller;

import com._z.eum.global.s3.S3UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@Tag(name = "이미지 업로트 테스트 API", description = "S3에 이미지 파일을 업로드 후 URL 반환 테스트용!")
@RequiredArgsConstructor
public class ImageController {

    private final S3UploadService s3UploadService;

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    @Operation(summary = "이미지 업로드", description = "S3에 이미지 업로드 후 URL 반환")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String imageUrl = s3UploadService.uploadFile("uploads", file);
        return ResponseEntity.ok(imageUrl);
    }

}
