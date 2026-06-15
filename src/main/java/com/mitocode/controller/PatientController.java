package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final IPatientService service;

    @GetMapping
    public ResponseEntity<List<Patient>> findAll(){
        List<Patient> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Patient patient){
        Patient obj = service.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id{")
    public ResponseEntity<Patient> update(@PathVariable Integer id, @RequestBody Patient patient){
        patient.setIdPatient(id);
        Patient obj = service.update(id, patient);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id{")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
