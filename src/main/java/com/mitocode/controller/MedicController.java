package com.mitocode.controller;

import com.mitocode.dto.MedicDTO;
import com.mitocode.model.Medic;
import com.mitocode.service.IMedicService;
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
@RequestMapping("/medics")
@RequiredArgsConstructor
public class MedicController {

    private final IMedicService service;
    @Qualifier("medicMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll(){
        List<MedicDTO> list = service.findAll().stream().map(this::converToDto).toList();
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id){
        Medic obj = service.findById(id);
        return ResponseEntity.ok(converToDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDTO dto){
        Medic obj = service.save(converToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody MedicDTO dto){
        dto.setIdMedic(id);
        Medic obj = service.update(id, converToEntity(dto));
        return ResponseEntity.ok(converToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private MedicDTO converToDto(Medic obj){
        return modelMapper.map(obj, MedicDTO.class);
    }

    private Medic converToEntity(MedicDTO dto){
        return modelMapper.map(dto, Medic.class);
    }

}
