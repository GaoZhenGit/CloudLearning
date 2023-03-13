package com.codetend.service.txseata.services.impl;

import com.codetend.common.response.BizException;
import com.codetend.common.response.ResponseResult;
import com.codetend.service.txseata.entity.FullStep;
import com.codetend.service.txseata.services.FeignOneService;
import com.codetend.service.txseata.services.FeignTwoService;
import com.codetend.service.txseata.services.ISeataMainService;
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
        ResponseResult<?> ret1 = feignOneService.addStep(fullStep.stepOne);
        if (ret1.getCode() != 0) {
            throw new BizException(ret1.getCode(), ret1.getMsg());
        }

        ResponseResult<?> ret2 = feignTwoService.addStep(fullStep.stepTwo);
        if (ret2.getCode() != 0) {
            throw new BizException(ret2.getCode(), ret2.getMsg());
        }
    }
}
