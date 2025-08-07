package com._z.eum.governmentSupport.controller;


import com._z.eum.governmentSupport.service.GovernmentSupportService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GovernmentSupportController {


    private final GovernmentSupportService governmentSupportService;

    public GovernmentSupportController(GovernmentSupportService governmentSupportService){
        this.governmentSupportService = governmentSupportService;
    }


}
