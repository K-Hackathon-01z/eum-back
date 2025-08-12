package com._z.eum.governmentSupport.service;


import com._z.eum.governmentSupport.repository.GovernmentSupportRepository;
import org.springframework.stereotype.Service;

@Service
public class GovernmentSupportService {

    private final GovernmentSupportRepository governmentSupportRepository;

    public GovernmentSupportService(GovernmentSupportRepository governmentSupportRepository){
        this.governmentSupportRepository = governmentSupportRepository;
    }




}
