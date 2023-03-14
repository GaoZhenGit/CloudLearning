package com.codetend.service.txseata.services.impl;

import com.codetend.common.response.BizException;
import com.codetend.service.txseata.config.SnowflakeDistributeId;
import com.codetend.service.txseata.entity.StepOneEntity;
import com.codetend.service.txseata.entity.StepTwoEntity;
import com.codetend.service.txseata.mapper.StepTwoMapper;
import com.codetend.service.txseata.services.IServiceSeataOneService;
import com.codetend.service.txseata.services.IServiceSeataTwoService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalLock;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @GlobalLock
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

    @SneakyThrows
    @Override
    @Transactional
    public void updateStep(StepTwoEntity stepTwoEntity, long amount) {
        Thread.sleep(3000);
        StepTwoEntity origin = getStep(stepTwoEntity.sid);
        if (origin == null) {
            throw new BizException(-404, "no step two:" + stepTwoEntity.sid);
        }
        if (origin.time + amount < 0) {
            throw new BizException(-513, "insufficient amount for step two");
        }
        log.info("step two from:{} to:{}", origin.time, origin.time + amount);
        origin.time += amount;
        stepTwoMapper.updateStep(origin);
    }

    @Override
    public void deleteStep(long sid) {
        stepTwoMapper.deleteStep(sid);
    }
}
