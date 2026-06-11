package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    // @Autowired
    private final PatientRepo repo;

    public Patient validAndSave(Patient patient){
        // repo = new PatientRepo();
        if(patient.getIdPatient() == 0){
            return repo.save(patient);
        }else{
            return new Patient();
        }
    }

}
