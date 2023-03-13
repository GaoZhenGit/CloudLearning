package com.codetend.service.txseata.services.impl;

import com.codetend.common.response.BizException;
import com.codetend.service.txseata.config.SnowflakeDistributeId;
import com.codetend.service.txseata.entity.StepOneEntity;
import com.codetend.service.txseata.mapper.StepOneMapper;
import com.codetend.service.txseata.services.IServiceSeataOneService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void deleteStep(long sid) {
        stepOneMapper.deleteStep(sid);
    }
}
