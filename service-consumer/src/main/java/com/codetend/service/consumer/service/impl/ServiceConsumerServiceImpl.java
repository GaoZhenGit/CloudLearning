package com.codetend.service.consumer.service.impl;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.service.consumer.service.IServiceConsumerService;
import com.codetend.service.consumer.service.RemoteFeignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServiceConsumerServiceImpl implements IServiceConsumerService {
    @Resource
    private RemoteFeignService remoteFeignService;
    @Override
    public CommonDataItem getRemoteData(String id) {
        return remoteFeignService.test(id);
    }
}
