package com.codetend.service.provider.service;

import com.codetend.common.entity.CommonDataItem;
import org.apache.rocketmq.client.producer.SendResult;

public interface IServiceProviderService {
    CommonDataItem test(String id);
    CommonDataItem sendClusterMsgByChannel(String topic, String message);
    CommonDataItem sendClusterMsgByBridge(String topic, String message);
    SendResult sendClusterMsgByNative(String topic, String tag, String message);
}
