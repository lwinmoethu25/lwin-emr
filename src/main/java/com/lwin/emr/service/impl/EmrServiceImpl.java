package com.lwin.emr.service.impl;

import java.util.List;
import static com.lwin.emr.domain.mapper.EmrMapper.INSTANCE;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lwin.emr.domain.dto.EmrDto;
import com.lwin.emr.domain.entity.EmrEntity;
import com.lwin.emr.exception.NotFoundException;
import com.lwin.emr.repository.EmrRepository;
import com.lwin.emr.service.EmrService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmrServiceImpl implements EmrService{

    private final EmrRepository emrRepository;

    @Override
    @Transactional
    public void uploadEmr(List<EmrDto> emrDtoList) {
        List<EmrEntity> emrEntities = INSTANCE.emrEntityList(emrDtoList);
        emrRepository.saveAll(emrEntities);
    }

    @Override
    public List<EmrDto> fetchEmr() {
        List<EmrEntity> emrEntities = emrRepository.findAll();
        
        if (emrEntities.isEmpty()) {
            throw new NotFoundException("No record found.");
        }
        return INSTANCE.emrDtoList(emrEntities);
    }

    @Override
    public EmrDto fetchEmrByCode(String code) {
        EmrEntity emrEntity = emrRepository.findByCode(code).orElseThrow(() -> new NotFoundException(code+ " - record not found."));
        return INSTANCE.emrDto(emrEntity);
    }

    @Override
    public void deleteAllEmr() {
        emrRepository.deleteAll();
    }
}
