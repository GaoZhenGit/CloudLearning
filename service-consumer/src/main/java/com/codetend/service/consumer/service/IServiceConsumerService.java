package com.codetend.service.consumer.service;

import com.codetend.common.entity.CommonDataItem;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.web.bind.annotation.RequestParam;


public interface IServiceConsumerService {
    CommonDataItem getRemoteData(String id);
    SendResult sendNativeMsg(String tag, String topic, String message);
}
