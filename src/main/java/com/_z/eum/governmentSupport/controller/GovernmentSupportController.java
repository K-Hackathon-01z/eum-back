package com._z.eum.governmentSupport.controller;


import com._z.eum.governmentSupport.dto.GovernmentSupportResponse;
import com._z.eum.governmentSupport.service.GovernmentSupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/supports")
public class GovernmentSupportController {

    private final GovernmentSupportService governmentSupportService;

    public GovernmentSupportController(GovernmentSupportService governmentSupportService){
        this.governmentSupportService = governmentSupportService;
    }


    @GetMapping("/recommend/{userId}")
    public ResponseEntity<List<GovernmentSupportResponse>> recommend(@PathVariable Integer userId) {
        System.out.println("[컨트롤러 진입] userId: " + userId);
        List<GovernmentSupportResponse> result = governmentSupportService.recommendSupports(userId);
        return ResponseEntity.ok(result);
    }
}
