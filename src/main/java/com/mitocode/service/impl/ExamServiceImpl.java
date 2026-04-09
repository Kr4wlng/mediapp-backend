package com.mitocode.service.impl;

import com.mitocode.model.Exam;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IExamRepo;
import com.mitocode.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {

    private final IExamRepo repo;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }

    @Override
    public Page<Exam> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
