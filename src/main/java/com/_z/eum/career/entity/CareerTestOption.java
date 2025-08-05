package com._z.eum.career.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Table(name = "career_test_option")
@Getter
@Setter
public class CareerTestOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private CareerTestQuestion question;

    private String text;
    private int orderNo;

    protected CareerTestOption(){}
}
