package com._z.eum.career.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "career_test_question")
@Getter
@Setter
public class CareerTestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;
    private int orderNo;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @OrderBy("orderNo ASC")
    private List<CareerTestOption> options = new ArrayList<>();


    protected CareerTestQuestion(){}
}
