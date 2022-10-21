package com.lwin.emr.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lwin.emr.domain.dto.EmrDto;
import com.lwin.emr.domain.entity.EmrEntity;


@Mapper
public interface EmrMapper {

    EmrMapper INSTANCE = Mappers.getMapper(EmrMapper.class);

    EmrDto emrDto(EmrEntity emrEntity);

    List<EmrDto> emrDtoList(List<EmrEntity> emrEntityList);

    List<EmrEntity> emrEntityList(List<EmrDto> emrDtoList);  
}
