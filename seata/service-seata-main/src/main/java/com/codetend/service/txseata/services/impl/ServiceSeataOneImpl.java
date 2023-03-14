package com.codetend.service.txseata.services.impl;

import com.codetend.common.response.BizException;
import com.codetend.service.txseata.config.SnowflakeDistributeId;
import com.codetend.service.txseata.entity.StepOneEntity;
import com.codetend.service.txseata.mapper.StepOneMapper;
import com.codetend.service.txseata.services.IServiceSeataOneService;
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
public class ServiceSeataOneImpl implements IServiceSeataOneService {
    @Autowired
    private StepOneMapper stepOneMapper;
    @Autowired
    private SnowflakeDistributeId snowflakeDistributeId;
    @Override
    public List<StepOneEntity> getSteps() {
        return stepOneMapper.getSteps();
    }

    @Override
    @GlobalLock
    public StepOneEntity getStep(long sid) {
        return stepOneMapper.getStep(sid);
    }

    @Override
    public void addStep(StepOneEntity stepOneEntity) {
        if (stepOneEntity.sid == 0L) {
            stepOneEntity.sid = snowflakeDistributeId.nextId();
        }
        log.info("new stepOne, xid:{}", RootContext.getXID());
        stepOneMapper.addStep(stepOneEntity);
        if (stepOneEntity.name.contains("err")) {
            throw new BizException(-511, "add step One error");
        }
    }

    @SneakyThrows
    @Override
    @Transactional
    public void updateStep(StepOneEntity stepOneEntity, long amount) {
        Thread.sleep(3000);
        StepOneEntity origin = getStep(stepOneEntity.sid);
        if (origin == null) {
            throw new BizException(-404, "no step one:" + stepOneEntity.sid);
        }
        if (origin.time + amount < 0) {
            throw new BizException(-513, "insufficient amount for step one");
        }
        log.info("step one from:{} to:{}", origin.time, origin.time + amount);
        origin.time += amount;
        stepOneMapper.updateStep(origin);
    }

    @Override
    public void deleteStep(long sid) {
        stepOneMapper.deleteStep(sid);
    }
}
