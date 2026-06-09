package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public Patient savePatient(){
        return service.validAndSave(new Patient(1, "Axl", "Durand"));
    }

}
