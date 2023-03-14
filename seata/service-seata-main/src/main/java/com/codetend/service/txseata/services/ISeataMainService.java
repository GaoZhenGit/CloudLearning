package com.codetend.service.txseata.services;

import com.codetend.service.txseata.entity.FullStep;

public interface ISeataMainService {
    void addDoubleSteps(FullStep fullStep);
    void tranStep(FullStep fullStep, long amount);
    FullStep getStep(FullStep fullStep);
}
