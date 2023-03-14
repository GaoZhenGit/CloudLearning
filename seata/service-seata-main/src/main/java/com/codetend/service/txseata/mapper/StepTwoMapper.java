package com.codetend.service.txseata.mapper;

import com.codetend.service.txseata.entity.StepTwoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StepTwoMapper {
    List<StepTwoEntity> getSteps();
    StepTwoEntity getStep(@Param("sid") long sid);
    void addStep(StepTwoEntity stepOneEntity);
    void updateStep(StepTwoEntity stepOneEntity);
    void deleteStep(@Param("sid") long sid);
}
