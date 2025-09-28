package com._z.eum.onedayClass.classes.entity;

import com._z.eum.skill.entity.SkillCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "artisan_id", nullable = false)
    private int artisanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillCategory skillCategory;

    private String title;
    private String photoUrl;
    private String description;
    private int price;
    private String location;
    private int capacity;
    private int interestedCount;
}
