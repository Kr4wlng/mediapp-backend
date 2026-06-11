package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
// @AllArgsConstructor
@RequiredArgsConstructor
public class PatientController {

    // @Autowired
    private final PatientService service;
    private String text;

    /* public PatientController(PatientService service) {
        this.service = service;
    } */

    @GetMapping
    public Patient savePatient(){
        // service = new PatientService();
        return service.validAndSave(new Patient(1, "Axl", "Durand"));
    }

}
