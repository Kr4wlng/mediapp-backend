package com.mitocode.repo;

import com.mitocode.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepo {

    public Patient save(Patient patient){
        System.out.println("Saving patient: " + patient);
        return patient;
    }

}
