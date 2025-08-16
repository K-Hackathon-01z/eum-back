
package com._z.eum.matching.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "artisan_id", nullable = false)
    private Integer artisanId;

    @Column(name = "matching_request_id", nullable = false)
    private Integer matchingRequestId;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "body", length = 500)
    private String body;

    @Column(name = "is_read", nullable = false)
    private boolean read;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "read_at")
    private LocalDateTime readAt;


    public Notification(Integer artisanId, Integer matchingRequestId, String type, String title, String body) {
        this.artisanId = artisanId;
        this.matchingRequestId = matchingRequestId;
        this.type = type;
        this.title = title;
        this.body = body;
        this.read = false;
        this.createdAt = LocalDateTime.now();
        this.readAt = null;
    }

    public void markRead() {
        if (!this.read) {
            this.read = true;
            this.readAt = LocalDateTime.now();
        }
    }

}
