package com._z.eum.user.service;

import com._z.eum.user.dto.request.LoginRequest;
import com._z.eum.user.dto.request.PasswordUpdateRequest;
import com._z.eum.user.dto.request.SignupRequest;
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


    //회원가입
    public void signup(SignupRequest signupRequest){
        if (userRepository.findByEmail(signupRequest.email()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        User newUser = new User(signupRequest);

        userRepository.save(newUser);
    }


    //로그인
    public boolean login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));

        return user.getPassword().equals(loginRequest.password());
    }


    //비밀번호 변경
    @Transactional
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest){
        User user = userRepository.findByEmail(passwordUpdateRequest.email())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 이메일입니다."));

        if (!user.getPassword().equals(passwordUpdateRequest.oldPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }

        user.setPassword(passwordUpdateRequest.newPassword());
    }

    // 전체 회원 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
