package com._z.eum.auth.controller;


import com._z.eum.auth.dto.request.EmailConfirmRequest;
import com._z.eum.auth.dto.request.EmailRequest;
import com._z.eum.auth.dto.request.LoginRequest;
import com._z.eum.auth.dto.request.SignupRequest;
import com._z.eum.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> requestEmail(@RequestBody EmailRequest request) {
        authService.sendVerificationCode(request.email());
        return ResponseEntity.ok("인증 메일 발송 완료되었습니다.");
    }

    // 이메일 인증 확인
    @PostMapping("/email/confirm")
    @Operation(summary = "이메일 인증 확인", description = "사용자가 받은 인증 코드를 검증합니다.")
    public ResponseEntity<String> confirmEmail(@RequestBody EmailConfirmRequest request) {
        authService.verifyCode(request.email(), request.code());
        return ResponseEntity.ok("이메일 인증 성공했습니다.");
    }

    // 회원가입
    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "이메일 인증이 완료된 사용자만 회원가입 가능")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공했습니다.");
    }

    // 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일 인증 여부 확인 후 로그인")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        authService.login(request.email());
        return ResponseEntity.ok("로그인 성공했습니다.");
    }



}
