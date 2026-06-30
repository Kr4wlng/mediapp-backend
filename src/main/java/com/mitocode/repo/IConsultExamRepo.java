package com.mitocode.repo;

import com.mitocode.model.ConsultExam;
import com.mitocode.model.ConsultExamPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IConsultExamRepo extends IGenericRepo<ConsultExam, ConsultExamPK> {

    @Transactional
    @Modifying // Esto es para INSERT, DELETE y UPDATE
    @Query(value = "INSERT INTO consult_exam(id_consult, id_exam) VALUES (:idConsult, :idExam)", nativeQuery = true)
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);

}
