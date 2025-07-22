package com._z.eum.skill.controller;


import com._z.eum.skill.dto.request.SkillCategoryCreateRequest;
import com._z.eum.skill.dto.request.SkillCategoryUpdateRequest;
import com._z.eum.skill.service.SkillService;
import com._z.eum.skill.dto.reponse.SkillCategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill-category")
@Tag(name = "기술 카테고리 API", description = "기술 카테고리 조회, 상세 정보 조회 기능 제공")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    //기술 카테고리 조회
    @Operation(summary = "전체 기술 카테고리 조회",description = "전체 기술을 카테고리순 > 이름 순으로 정렬하여 카테고리, 이름, 사진 제공함")
    @GetMapping
    public ResponseEntity<List<SkillCategoryResponse>> getAllSkills(){
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    //기술 단건 조회
    @Operation(summary = "기술 카테고리 단건 조회", description = "ID를 이용해 특정 기술의 상세정보 조회함")
    @GetMapping("/{id}")
    public ResponseEntity<SkillCategoryResponse> getOneSkill(@PathVariable int id) {
        return ResponseEntity.ok(skillService.getOneSkill(id));
    }


    //관리자 모드 : 기술 카테고리 등록
    @Operation(summary = "[관리자모드] 기술 카테고리 등록", description = "기술 카테고리 등록")
    @PostMapping("/admin")
    public ResponseEntity<SkillCategoryResponse> createSkill(@RequestBody SkillCategoryCreateRequest request) {
        return ResponseEntity.ok(skillService.createSkill(request));
    }

    //관리자 모드 : 기술 카테고리 수정
    @Operation(summary = "[관리자] 기술 카테고리 수정", description = "특정 기술 카테고리 정보를 수정")
    @PutMapping("/admin/{name}")
    public ResponseEntity<SkillCategoryResponse> updateSkill(@PathVariable String skillName, @RequestBody SkillCategoryUpdateRequest request) {
        return ResponseEntity.ok(skillService.updateSkill(skillName,request));
    }

    //관리자 모드 : 기술 카테고리 삭제
    @Operation(summary = "[관리자] 기술 카테고리 삭제", description = "특정 기술 카테고리를 삭제")
    @DeleteMapping("/admin/{name}")
    public ResponseEntity<String> deleteSkill(@PathVariable String name) {
        return ResponseEntity.ok(skillService.deleteSkill(name));
    }

}
