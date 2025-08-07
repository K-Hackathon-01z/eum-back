package com._z.eum.governmentSupport.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class GovernmentSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    private String targetAge;

    private String targetLocation;

    @Column(columnDefinition = "TEXT")
    private String url;

    private String sourceApi;


}
