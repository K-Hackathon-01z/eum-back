package com._z.eum.onedayClass.classes.service;

import com._z.eum.onedayClass.classes.dto.request.ClassesRequest;
import com._z.eum.onedayClass.classes.dto.response.ClassesResponse;
import com._z.eum.onedayClass.classes.entity.Classes;
import com._z.eum.onedayClass.classes.repository.ClassesRepository;
import com._z.eum.skill.entity.SkillCategory;
import com._z.eum.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassesRepository classesRepository;
    private final SkillRepository skillCategoryRepository;

    // 전체 조회
    public List<ClassesResponse> getAllClasses() {
        return classesRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    // 단건 조회
    public ClassesResponse getClassById(int id) {
        return classesRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스가 존재하지 않습니다."));
    }

    // 생성 (기존)
    public ClassesResponse createClass(ClassesRequest dto) {
        return createClassWithUrl(dto, dto.photoUrl());
    }

    // 생성 (기본 이미지 처리 포함)
    public ClassesResponse createClassWithUrl(ClassesRequest dto, String photoUrl) {
        SkillCategory skillCategory = skillCategoryRepository.findById(dto.skillId())
                .orElseThrow(() -> new IllegalArgumentException("해당 스킬이 존재하지 않습니다."));

        Classes entity = Classes.builder()
                .artisanId(dto.artisanId())
                .skillCategory(skillCategory)
                .title(dto.title())
                .photoUrl(photoUrl)
                .description(dto.description())
                .price(dto.price())
                .location(dto.location())
                .capacity(dto.capacity())
                .interestedCount(0)
                .build();

        return toResponse(classesRepository.save(entity));
    }


    // 수정
    public ClassesResponse updateClass(int id, ClassesRequest dto) {
        Classes entity = classesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 클래스가 존재하지 않습니다."));

        SkillCategory skillCategory = skillCategoryRepository.findById(dto.skillId())
                .orElseThrow(() -> new IllegalArgumentException("해당 스킬이 존재하지 않습니다."));

        entity.setArtisanId(dto.artisanId());
        entity.setSkillCategory(skillCategory);
        entity.setTitle(dto.title());
        entity.setPhotoUrl(dto.photoUrl());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setLocation(dto.location());
        entity.setCapacity(dto.capacity());

        return toResponse(classesRepository.save(entity));
    }

    // 삭제
    public void deleteClass(int id) {
        if (!classesRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 클래스가 존재하지 않습니다.");
        }
        classesRepository.deleteById(id);
    }

    private ClassesResponse toResponse(Classes entity) {
        return new ClassesResponse(
                entity.getId(),
                entity.getSkillCategory() != null ? entity.getSkillCategory().getId() : 0,
                entity.getArtisanId(),
                entity.getTitle(),
                entity.getPhotoUrl(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getLocation(),
                entity.getCapacity(),
                entity.getInterestedCount()
        );
    }

    private Classes toEntity(ClassesRequest dto) {
        SkillCategory skillCategory = skillCategoryRepository.findById(dto.skillId())
                .orElseThrow(() -> new IllegalArgumentException("해당 스킬이 존재하지 않습니다."));

        return Classes.builder()
                .artisanId(dto.artisanId())
                .skillCategory(skillCategory)
                .title(dto.title())
                .photoUrl(dto.photoUrl())
                .description(dto.description())
                .price(dto.price())
                .location(dto.location())
                .capacity(dto.capacity())
                .build();
    }
}
