package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepo repo;

    public Patient validAndSave(Patient patient){
        if(patient.getIdPatient() == 0){
            return repo.save(patient);
        }else{
            return new Patient();
        }
    }

}
