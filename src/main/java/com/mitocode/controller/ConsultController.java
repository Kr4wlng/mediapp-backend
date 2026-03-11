package com.mitocode.controller;

import com.mitocode.dto.ConsultDTO;
import com.mitocode.dto.ConsultListExamDTO;
import com.mitocode.dto.FilterConsultDTO;
import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.service.IConsultService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {

    private final IConsultService service;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll(){
        List<ConsultDTO> list = mapperUtil.mapList(service.findAll(), ConsultDTO.class, "consultMapper");
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id){
        Consult obj = service.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ConsultDTO.class, "consultMapper"));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ConsultListExamDTO dto){
        Consult obj = service.saveTransactional(mapperUtil.map(dto.getConsult(), Consult.class, "consultMapper"), mapperUtil.mapList(dto.getLstExam(), Exam.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ConsultDTO dto){
        dto.setIdConsult(id);
        Consult obj = service.update(id, mapperUtil.map(dto, Consult.class, "consultMapper"));
        return ResponseEntity.ok(mapperUtil.map(obj, ConsultDTO.class, "consultMapper"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* =============== Queries ================== */

    @PostMapping("/search/others")
    public ResponseEntity<List<ConsultDTO>> searchByOthers(@RequestBody FilterConsultDTO dto){
        List<Consult> list = service.search(dto.getDni(), dto.getFullname());
        List<ConsultDTO> listDTO = mapperUtil.mapList(list, ConsultDTO.class, "consultMapper");

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/search/dates")
    public ResponseEntity<List<ConsultDTO>> searchByDates(
            @RequestParam(value = "date1", defaultValue = "2026-03-01") String date1,
            @RequestParam(value = "date2", defaultValue = "2026-03-15") String date2
    ){
        List<Consult> list = service.searchByDates(LocalDateTime.parse(date1), LocalDateTime.parse(date2));
        List<ConsultDTO> listDTO = mapperUtil.mapList(list, ConsultDTO.class, "consultMapper");

        return ResponseEntity.ok(listDTO);
    }

}
