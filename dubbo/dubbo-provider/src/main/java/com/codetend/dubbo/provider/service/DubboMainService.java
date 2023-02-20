package com.codetend.dubbo.provider.service;

import com.codetend.common.entity.CommonDataItem;

public interface DubboMainService {
    CommonDataItem getData();
    CommonDataItem provideKafkaMessage(CommonDataItem commonDataItem);
}
