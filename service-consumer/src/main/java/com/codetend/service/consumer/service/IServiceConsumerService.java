package com.codetend.service.consumer.service;

import com.codetend.common.entity.CommonDataItem;


public interface IServiceConsumerService {
    CommonDataItem getRemoteData(String id);
}
