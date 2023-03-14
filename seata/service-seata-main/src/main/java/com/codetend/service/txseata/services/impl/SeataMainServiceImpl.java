package com.codetend.service.txseata.services.impl;

import com.codetend.common.response.BizException;
import com.codetend.common.response.ResponseResult;
import com.codetend.service.txseata.entity.FullStep;
import com.codetend.service.txseata.entity.StepOneEntity;
import com.codetend.service.txseata.entity.StepTwoEntity;
import com.codetend.service.txseata.services.FeignOneService;
import com.codetend.service.txseata.services.FeignTwoService;
import com.codetend.service.txseata.services.ISeataMainService;
import io.seata.spring.annotation.GlobalLock;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeataMainServiceImpl implements ISeataMainService {
    @Autowired
    private FeignOneService feignOneService;
    @Autowired
    private FeignTwoService feignTwoService;
    @Override
    @GlobalTransactional
    public void addDoubleSteps(FullStep fullStep) {
        check(feignOneService.addStep(fullStep.stepOne));
        check(feignTwoService.addStep(fullStep.stepTwo));
    }

    @Override
    public FullStep getStep(FullStep fullStep) {
        StepOneEntity one = check(feignOneService.getStep(fullStep.stepOne.sid));
        StepTwoEntity two = check(feignTwoService.getStep(fullStep.stepTwo.sid));
        return new FullStep(one, two);
    }

    @Override
    @GlobalTransactional
    public void tranStep(FullStep fullStep, long amount) {
        check(feignOneService.updateStep(fullStep.stepOne, -amount));
        check(feignTwoService.updateStep(fullStep.stepTwo, amount));
    }

    private <T> T check(ResponseResult<T> ret) {
        if (ret.getCode() != 0) {
            throw new BizException(ret.getCode(), ret.getMsg());
        }
        return ret.getData();
    }
}
