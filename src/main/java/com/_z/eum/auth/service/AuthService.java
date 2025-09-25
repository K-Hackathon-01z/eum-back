package com._z.eum.auth.service;

import com._z.eum.auth.entity.VerificationToken;
import com._z.eum.auth.repository.AuthRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;


@Service
public class AuthService {


    private final AuthRepository authRepository;
    private final JavaMailSender javaMailSender;

    public AuthService(AuthRepository authRepository,
                       JavaMailSender javaMailSender){
        this.authRepository = authRepository;
        this.javaMailSender = javaMailSender;

    }

    // 이메일 인증 코드 발송
    public void sendVerificationCode(String email) {

        //코드 생성
        String code = generateCode();

        //생성된 코드 DB 저장
        VerificationToken token = new VerificationToken(email, code, 5);
        authRepository.save(token);

        //이메일 전송
        sendEmail(email, code);
    }

    // 이메일 인증 확인
    public void verifyCode(String email, String code) {

        //DB에서 생성된 코드 조회
        VerificationToken token = authRepository.findTopByEmailOrderByCreatedAtDesc(email)
                .orElseThrow(() -> new RuntimeException("인증 요청이 없습니다."));


        //인증 코드 만료 확인
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("인증 코드 만료되었습니다.");
        }

        //인증 코드 불일치 확인
        if (!token.getCode().equals(code)) {
            throw new RuntimeException("인증 코드가 불일치합니다.");
        }

        //인증 정보 저장
        token.setVerified(true);
        authRepository.save(token);
    }


    //코드 생성
    private String generateCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000); // 6자리
    }

    private void sendEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("vvin506@naver.com");
        message.setSubject("[이음] 이메일 인증 코드");
        message.setText("인증 코드: " + code);
        javaMailSender.send(message);
    }




}
