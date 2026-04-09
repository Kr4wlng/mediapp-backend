package com.mitocode.service;

import com.mitocode.model.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IExamService extends ICRUD<Exam, Integer>{

    Page<Exam> listPage(Pageable pageable);

}
