package com.codetend.service.txseata.services.impl;

import com.codetend.common.response.BizException;
import com.codetend.service.txseata.config.SnowflakeDistributeId;
import com.codetend.service.txseata.entity.StepOneEntity;
import com.codetend.service.txseata.entity.StepTwoEntity;
import com.codetend.service.txseata.mapper.StepTwoMapper;
import com.codetend.service.txseata.services.IServiceSeataOneService;
import com.codetend.service.txseata.services.IServiceSeataTwoService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiceSeataTwoImpl implements IServiceSeataTwoService {
    @Autowired
    private StepTwoMapper stepTwoMapper;
    @Autowired
    private SnowflakeDistributeId snowflakeDistributeId;
    @Override
    public List<StepTwoEntity> getSteps() {
        return stepTwoMapper.getSteps();
    }

    @Override
    public StepTwoEntity getStep(long sid) {
        return stepTwoMapper.getStep(sid);
    }

    @Override
    public void addStep(StepTwoEntity stepTwoEntity) {
        if (stepTwoEntity.sid == 0L) {
            stepTwoEntity.sid = snowflakeDistributeId.nextId();
        }
        log.info("new stepTwo, xid:{}", RootContext.getXID());
        stepTwoMapper.addStep(stepTwoEntity);
        if (stepTwoEntity.name.contains("err")) {
            throw new BizException(-511, "add step Two error");
        }
    }

    @Override
    public void deleteStep(long sid) {
        stepTwoMapper.deleteStep(sid);
    }
}
