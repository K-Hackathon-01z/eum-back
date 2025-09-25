package com._z.eum.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일
    private String email;

    // 인증 코드
    private String code;

    // 인증 여부
    private boolean verified = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    // 만료 시간
    private LocalDateTime expiresAt;

    protected VerificationToken() {}

    public VerificationToken(String email, String code, int expireMinutes) {
        this.email = email;
        this.code = code;
        this.expiresAt = LocalDateTime.now().plusMinutes(expireMinutes);
    }
}


