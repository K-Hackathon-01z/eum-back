package com._z.eum.governmentSupport.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "government_support")
public class GovernmentSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    private String targetAge;

    private String targetLocation;

    @Column(columnDefinition = "TEXT")
    private String url;

    private String sourceApi;

    protected GovernmentSupport(){}


    public GovernmentSupport(String title, String description, String category,
                             String targetAge, String targetLocation, String url, String sourceApi) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.targetAge = targetAge;
        this.targetLocation = targetLocation;
        this.url = url;
        this.sourceApi = sourceApi;
    }

    // 필요한 경우 setter 대신 update 메서드로 수정할 수 있음
    public void update(String title, String description, String category,
                       String targetAge, String targetLocation, String url) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.targetAge = targetAge;
        this.targetLocation = targetLocation;
        this.url = url;
    }
}
