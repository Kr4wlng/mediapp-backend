package com.mitocode.service.impl;

import com.mitocode.model.Patient;
import com.mitocode.repo.IPatientRepo;
import com.mitocode.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {

    private final IPatientRepo repo;

    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient update(Integer id, Patient patient) {
        // Falta agregar validación
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        return repo.findById(id).orElse(new Patient());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
