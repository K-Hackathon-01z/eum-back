package com._z.eum.user.controller;


import com._z.eum.user.dto.request.LoginRequest;
import com._z.eum.user.dto.request.PasswordUpdateRequest;
import com._z.eum.user.dto.request.SignupRequest;
import com._z.eum.user.entity.User;
import com._z.eum.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "사용자 API", description = "회원가입, 로그인, 비밀번호 기능 제공")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    @Operation(summary = "사용자 회원가입", description = "이메일, 이름, 나이, 주소, 비밀번호를 받아 회원가입 성공여부 반환")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok("회원가입 성공하였습니다.");
    }

    // 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "아이디 비밀번호 받아 로그인 성공 여부를 반환")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean success = userService.login(request);
        if (success) {
            return ResponseEntity.ok("로그인 성공하였습니다.");
        } else {
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
        }
    }

    // 비밀번호 변경
    @PostMapping("/update-password")
    @Operation(summary = "비밀번호 변경", description = "이메일, 기존 비밀번호, 새로운 비밀번호를 받아 변경 성공 여부를 반환")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request) {
        userService.updatePassword(request);
        return ResponseEntity.ok("비밀번호 변경 성공하였습니다.");
    }


    // 전체 회원 조회
    @GetMapping("/all")
    @Operation(summary = "전체 회원 조회", description = "저장된 전체 회원의 정보 조회")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
