package com._z.eum.skill.service;

import com._z.eum.skill.dto.reponse.SkillCategoryResponse;
import com._z.eum.skill.dto.request.SkillCategoryCreateRequest;
import com._z.eum.skill.entity.SkillCategory;
import com._z.eum.skill.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillCategoryResponse> getAllSkills(){

        //엔티티 리스트
        List<SkillCategory> skillCategoryList = skillRepository.findAllByOrderByCategoryAscNameAsc();

        //응답 리스트
        List<SkillCategoryResponse> skillCategoryResponses = new ArrayList<>();

        for (SkillCategory skillCategory : skillCategoryList) {
            SkillCategoryResponse dto = new SkillCategoryResponse(
                    skillCategory.getId(),
                    skillCategory.getName(),
                    skillCategory.getCategory(),
                    skillCategory.getDescription(),
                    skillCategory.getImageUrl(),
                    skillCategory.getCareerPath());

            skillCategoryResponses.add(dto);
        }
        return skillCategoryResponses;
    }


    //기술 단건 조회
    public SkillCategoryResponse getOneSkill(int id){

        //아이디로 조회
        SkillCategory skillCategory = skillRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 id의 기술이 존재하지 않음"));

        return new SkillCategoryResponse(
                skillCategory.getId(),
                skillCategory.getName(),
                skillCategory.getCategory(),
                skillCategory.getDescription(),
                skillCategory.getImageUrl(),
                skillCategory.getCareerPath());

    }

    public SkillCategoryResponse createSkill(SkillCategoryCreateRequest request) {

        //요청 데이터를 기반으로 엔티티 생성
        SkillCategory entity = new SkillCategory(
                request.name(),
                request.category(),
                request.description(),
                request.imageUrl(),
                request.careerPath()
        );

        // DB에 저장
        SkillCategory savedEntity = skillRepository.save(entity);

        return new SkillCategoryResponse(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getCategory(),
                savedEntity.getDescription(),
                savedEntity.getImageUrl(),
                savedEntity.getCareerPath()
        );
    }
}
