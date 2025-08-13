package com._z.eum.governmentSupport.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "userSupport_result")
public class UserSupportResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support_id", nullable = false)
    private GovernmentSupport support;

    @Column(nullable = false)
    private LocalDateTime recommendedAt;

    @Column(nullable = false)
    private boolean isRead = false;

    protected UserSupportResult(){}


    public UserSupportResult(Integer userId, GovernmentSupport support, LocalDateTime recommendedAt, boolean isRead) {
        this.userId = userId;
        this.support = support;
        this.recommendedAt = recommendedAt;
        this.isRead = isRead;
    }

}
