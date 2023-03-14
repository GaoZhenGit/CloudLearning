package com.codetend.service.txseata.mapper;

import com.codetend.service.txseata.entity.StepOneEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StepOneMapper {
    List<StepOneEntity> getSteps();
    StepOneEntity getStep(@Param("sid") long sid);
    void addStep(StepOneEntity stepOneEntity);
    void updateStep(StepOneEntity stepOneEntity);
    void deleteStep(@Param("sid") long sid);
}
