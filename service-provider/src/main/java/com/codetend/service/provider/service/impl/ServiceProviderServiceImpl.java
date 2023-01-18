package com.codetend.service.provider.service.impl;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.service.provider.service.IServiceProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderServiceImpl implements IServiceProviderService {
    @Value("${spring.application.name}")
    private String moduleName;
    @Override
    public CommonDataItem test(String id) {
        return new CommonDataItem(id, String.format("data from:%s", moduleName));
    }
}
