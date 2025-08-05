package com._z.eum.career.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "careerTestResult")
public class CareerTestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  Integer userId;

    private LocalDateTime updatedAt = LocalDateTime.now();

    private String summary;

    @OneToMany(mappedBy = "careerTestResult", cascade = CascadeType.ALL)
    private List<CareerTestRecommendation> recommendations = new ArrayList<>();

    public CareerTestResult(Integer userId) {
        this.userId = userId;
        this.updatedAt = LocalDateTime.now();
    }

}
