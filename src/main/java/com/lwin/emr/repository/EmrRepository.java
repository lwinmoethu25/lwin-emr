package com.lwin.emr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lwin.emr.domain.entity.EmrEntity;

@Repository
public interface EmrRepository extends JpaRepository<EmrEntity, Long>{

    Optional<EmrEntity> findByCode(String code); 
}
