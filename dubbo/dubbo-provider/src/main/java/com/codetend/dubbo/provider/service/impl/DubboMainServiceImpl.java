package com.codetend.dubbo.provider.service.impl;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.dubbo.provider.service.DubboMainService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Service
@Component
public class DubboMainServiceImpl implements DubboMainService {
    @Value("${server.port}")
    private int appPort;
    private volatile int count = 0;

    @Override
    public CommonDataItem getData() {
        count++;
        return CommonDataItem.builder()
                .id("dubbo")
                .topic(String.valueOf(count))
                .msg("from dubbo provider:" + appPort)
                .build();
    }
}
