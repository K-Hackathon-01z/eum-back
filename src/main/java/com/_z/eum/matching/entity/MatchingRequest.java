// src/main/java/com/_z/eum/matching/entity/MatchingRequest.java
package com._z.eum.matching.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "matching_request")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED) // JPA 기본 생성자
public class MatchingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "artisan_id", nullable = false)
    private Integer artisanId;

    @Lob
    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    @Size(max = 250)
    private String message;

    @Column(name = "is_anonymous", nullable = false)
    private boolean anonymous;

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;


    @Column(name = "is_read", nullable = false)
    private boolean read;

    @Column(name = "read_at")
    private LocalDateTime readAt;


    public MatchingRequest(Integer userId, Integer artisanId, String message, boolean anonymous) {
        this.userId = userId;
        this.artisanId = artisanId;
        this.message = message;
        this.anonymous = anonymous;
        this.requestDate = LocalDateTime.now();
        this.read = false;
        this.readAt = null;
    }

    public void markRead() {
        if (!this.read) {
            this.read = true;
            this.readAt = LocalDateTime.now();
        }
    }
}
