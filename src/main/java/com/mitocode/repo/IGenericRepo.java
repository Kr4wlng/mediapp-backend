package com.mitocode.repo;

import com.mitocode.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID> {



}
