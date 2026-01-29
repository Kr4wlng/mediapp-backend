package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
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
    /* @Qualifier("defaultMapper")
    private final ModelMapper modelMapper; */
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll(){
        // List<PatientDTO> list = service.findAll().stream().map(this::converToDto).toList();
       List<PatientDTO> list = mapperUtil.mapList(service.findAll(), PatientDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id){
        Patient obj = service.findById(id);
        return ResponseEntity.ok(converToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PatientDTO dto){
        Patient obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PatientDTO dto){
        dto.setIdPatient(id);
        Patient obj = service.update(id, converToEntity(dto));
        return ResponseEntity.ok(converToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PatientDTO converToDto(Patient obj){
        return modelMapper.map(obj, PatientDTO.class);
    }

    private Patient converToEntity(PatientDTO dto){
        return modelMapper.map(dto, Patient.class);
    }

}
