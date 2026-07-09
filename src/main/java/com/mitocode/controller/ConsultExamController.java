package com.mitocode.controller;

import com.mitocode.dto.ConsultExamDTO;
import com.mitocode.model.ConsultExam;
import com.mitocode.service.IConsultExamService;
import com.mitocode.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consultexams")
@RequiredArgsConstructor
public class ConsultExamController {

    private final IConsultExamService service;
    private final MapperUtil mapperUtil;

    @GetMapping("/{idConsult}")
    public ResponseEntity<List<ConsultExamDTO>> getConsultsById(@PathVariable("idConsult") Integer idConsult){
        List<ConsultExam> list = service.getExamsByConsultId(idConsult);
        List<ConsultExamDTO> listDTO = mapperUtil.mapList(list, ConsultExamDTO.class);

        return ResponseEntity.ok(listDTO);
    }

}
