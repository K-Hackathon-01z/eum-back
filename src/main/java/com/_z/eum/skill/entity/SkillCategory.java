package com._z.eum.skill.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "skill_category" )
@Getter
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private  String category;
    String description;
    String imageUrl;
    String careerPath;

    protected SkillCategory(){}

    public SkillCategory(String name, String category, String description, String imageUrl, String careerPath){
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.careerPath = careerPath;
    }

    public void updateSkill(String category, String description, String imageUrl, String careerPath) {
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.careerPath = careerPath;
    }
}
