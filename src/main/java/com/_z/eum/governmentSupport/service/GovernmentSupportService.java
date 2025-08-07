package com._z.eum.governmentSupport.service;


import com._z.eum.governmentSupport.repository.SupportRepository;
import org.springframework.stereotype.Service;

@Service
public class GovernmentSupportService {

    private final SupportRepository supportRepository;

    public GovernmentSupportService(SupportRepository supportRepository){
        this.supportRepository = supportRepository;
    }




}
