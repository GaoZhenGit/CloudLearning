package com.codetend.service.txseata.services;

import com.codetend.service.txseata.entity.StepTwoEntity;

import java.util.List;

public interface IServiceSeataTwoService {
    List<StepTwoEntity> getSteps();
    StepTwoEntity getStep(long sid);
    void addStep(StepTwoEntity stepTwoEntity);
    void updateStep(StepTwoEntity stepTwoEntity, long amount);
    void deleteStep(long sid);
}
