package com._z.eum.auth.controller;


import com._z.eum.auth.dto.request.EmailConfirmRequest;
import com._z.eum.auth.dto.request.EmailRequest;
import com._z.eum.auth.dto.response.AuthResponse;
import com._z.eum.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "인증 API",description = "회원가입, 로그인, 이메일 인증 기능 제공")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }


    // 이메일 인증 코드 요청
    @PostMapping("/email/request")
    @Operation(summary = "이메일 인증 코드 요청", description = "사용자가 이메일을 입력하면 인증 코드를 발송합니다.")
    public ResponseEntity<AuthResponse> requestEmail(@RequestBody EmailRequest request) {
        authService.sendVerificationCode(request.email());
        return ResponseEntity.ok(new AuthResponse(true, "인증 메일 발송 완료"));
    }

    // 이메일 인증 확인
    @PostMapping("/email/confirm")
    @Operation(summary = "이메일 인증 확인", description = "사용자가 받은 인증 코드를 검증합니다.")
    public ResponseEntity<AuthResponse> confirmEmail(@RequestBody EmailConfirmRequest request) {
        authService.verifyCode(request.email(), request.code());
        return ResponseEntity.ok(new AuthResponse(true, "이메일 인증 성공"));
    }



}
