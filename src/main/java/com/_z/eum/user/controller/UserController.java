package com._z.eum.user.controller;

import com._z.eum.user.entity.User;
import com._z.eum.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "사용자 API", description = "회원가입, 로그인, 비밀번호 변경, 사용자 조회 기능 제공")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // 회원 삭제
    @DeleteMapping("/{email}")
    @Operation(summary = "사용자 삭제", description = "사용자 삭제 기능")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("회원 삭제 성공하였습니다.");
    }

    // 전체 회원 조회
    @GetMapping("/all")
    @Operation(summary = "전체 회원 조회", description = "저장된 전체 회원의 정보 조회")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }



    // 이메일로 단일 회원 조회
    @GetMapping("/{email}")
    @Operation(summary = "이메일로 단일 회원 조회", description = "사용자 이메일로 해당 사용자의 정보 조회")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

}
