package com.mitocode.controller;

import com.mitocode.dto.SpecialtyDTO;
import com.mitocode.model.Specialty;
import com.mitocode.service.ISpecialtyService;
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
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final ISpecialtyService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> findAll(){
        List<SpecialtyDTO> list = service.findAll().stream().map(this::converToDto).toList();
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable("id") Integer id){
        Specialty obj = service.findById(id);
        return ResponseEntity.ok(converToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SpecialtyDTO dto){
        Specialty obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody SpecialtyDTO dto){
        dto.setIdSpecialty(id);
        Specialty obj = service.update(id, converToEntity(dto));
        return ResponseEntity.ok(converToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private SpecialtyDTO converToDto(Specialty obj){
        return modelMapper.map(obj, SpecialtyDTO.class);
    }

    private Specialty converToEntity(SpecialtyDTO dto){
        return modelMapper.map(dto, Specialty.class);
    }

}
