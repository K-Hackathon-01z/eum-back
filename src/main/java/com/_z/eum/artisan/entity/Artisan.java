// src/main/java/com/_z/eum/artisan/entity/Artisan.java
package com._z.eum.artisan.entity;

import com._z.eum.artisan.dto.request.ArtisanRequest;
import com._z.eum.skill.entity.SkillCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artisan")
@Getter
public class Artisan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "skill_id", nullable = false)
    private Integer skillId;

    private String email;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "photo_url", nullable = false, length = 1000)
    private String photoUrl;

    @Column(name = "main_works", columnDefinition = "TEXT")
    private String mainWorks;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    protected Artisan() {
    }

    public Artisan(ArtisanRequest artisanRequest) {
        this.name = artisanRequest.name();
        this.photoUrl = artisanRequest.photoUrl();
        this.mainWorks = artisanRequest.mainWorks();
        this.biography = artisanRequest.biography();
        this.skillId = artisanRequest.skillId();
        this.email = artisanRequest.email();
    }

    public void updateArtisanInfo(String photoUrl, String mainWorks, String biography) {
        this.photoUrl = photoUrl;
        this.mainWorks = mainWorks;
        this.biography = biography;
    }
}
