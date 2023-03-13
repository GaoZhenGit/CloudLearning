package com.codetend.service.txseata.services;

import com.codetend.service.txseata.entity.StepTwoEntity;

import java.util.List;

public interface IServiceSeataTwoService {
    List<StepTwoEntity> getSteps();
    StepTwoEntity getStep(long sid);
    void addStep(StepTwoEntity StepTwoEntity);
    void deleteStep(long sid);
}
