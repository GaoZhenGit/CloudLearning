package com.codetend.dubbo.consumer.service.impl;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.dubbo.consumer.service.IDubboConsumerService;
import com.codetend.dubbo.provider.service.DubboMainService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class DubboConsumerServiceImpl implements IDubboConsumerService {
    @Reference
    private DubboMainService dubboMainService;
    @Override
    public CommonDataItem remote(String id) {
        return dubboMainService.getData();
    }
}
