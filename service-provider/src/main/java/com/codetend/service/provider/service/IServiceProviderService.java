package com.codetend.service.provider.service;

import com.codetend.common.entity.CommonDataItem;

public interface IServiceProviderService {
    CommonDataItem test(String id);
    CommonDataItem sendClusterMsgByChannel(String topic, String message);
    CommonDataItem sendClusterMsgByBridge(String topic, String message);
}
