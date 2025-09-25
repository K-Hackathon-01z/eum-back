package com._z.eum.user.service;

import com._z.eum.user.dto.request.UserInfoUpdateRequest;
import com._z.eum.user.dto.response.UserResponse;
import com._z.eum.user.entity.User;
import com._z.eum.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // 전체 회원 조회
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 이메일로 단일 회원 조회
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("해당 이메일의 회원이 존재하지 않습니다."));
        return toResponse(user);
    }

    // 회원 삭제
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("해당 이메일의 회원이 존재하지 않습니다."));
        userRepository.delete(user);
    }

    // ID로 회원 조회
    public UserResponse getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("해당 아이디의 회원이 존재하지 않습니다."));
        return toResponse(user);
    }

    // 사용자 정보 변경
    @Transactional
    public UserResponse updateUserInfo(String email, UserInfoUpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("해당 이메일의 회원이 존재하지 않습니다."));

        if (request.name() != null && !request.name().isBlank()) {
            user.setName(request.name());
        }
        if (request.age() > 0) {
            user.setAge(request.age());
        }
        if (request.gender() != null && !request.gender().isBlank()) {
            user.setGender(request.gender());
        }
        if (request.address() != null && !request.address().isBlank()) {
            user.setAddress(request.address());
        }

        return toResponse(user);
    }

    // Entity → DTO 변환
    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getName(),
                user.getAge(),
                user.getGender(),
                user.getAddress()
        );
    }
}
