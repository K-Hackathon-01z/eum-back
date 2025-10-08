package com._z.eum.artisan.entity;

import com._z.eum.artisan.dto.request.ArtisanRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artisan")
@Getter
@NoArgsConstructor
public class Artisan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_id", nullable = false)
    private Integer skillId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "photo_url", length = 1000)
    private String photoUrl;

    @Column(name = "main_works", columnDefinition = "TEXT")
    private String mainWorks;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    public Artisan(ArtisanRequest request, String photoUrl) {
        this.skillId = request.skillId();
        this.email = request.email();
        this.name = request.name();
        this.photoUrl = photoUrl;
        this.mainWorks = request.mainWorks();
        this.biography = request.biography();
    }

    public void updateArtisanInfo(String photoUrl, String mainWorks, String biography) {
        if (photoUrl != null && !photoUrl.isBlank()) this.photoUrl = photoUrl;
        if (mainWorks != null) this.mainWorks = mainWorks;
        if (biography != null) this.biography = biography;
    }
}
