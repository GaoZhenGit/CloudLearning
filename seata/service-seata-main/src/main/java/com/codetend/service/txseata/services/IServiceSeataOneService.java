package com.codetend.service.txseata.services;

import com.codetend.service.txseata.entity.StepOneEntity;

import java.util.List;

public interface IServiceSeataOneService {
    List<StepOneEntity> getSteps();
    StepOneEntity getStep(long sid);
    void addStep(StepOneEntity stepOneEntity);
    void deleteStep(long sid);
}
