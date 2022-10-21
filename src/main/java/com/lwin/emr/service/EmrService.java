package com.lwin.emr.service;

import java.util.List;

import com.lwin.emr.domain.dto.EmrDto;

public interface EmrService {

    void uploadEmr(List<EmrDto> emrDto);

    List<EmrDto> fetchEmr();
     
    EmrDto fetchEmrByCode(String code);

    void deleteAllEmr();    
}
