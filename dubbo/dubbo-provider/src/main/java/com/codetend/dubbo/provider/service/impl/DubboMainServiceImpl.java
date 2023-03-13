package com.codetend.dubbo.provider.service.impl;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.dubbo.provider.service.DubboMainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.atomic.AtomicInteger;

@Service(version = "1.0.0")
@Component
@Slf4j
public class DubboMainServiceImpl implements DubboMainService {
    @Value("${server.port}")
    private int appPort;
    private final AtomicInteger count = new AtomicInteger(0);
    @Autowired
    private KafkaTemplate<String, CommonDataItem> kafkaTemplate;

    @Override
    public CommonDataItem getData() {
        return CommonDataItem.builder()
                .id("dubbo")
                .topic(String.valueOf(count.getAndIncrement()))
                .msg("from dubbo provider:" + appPort)
                .build();
    }

    @Override
    public CommonDataItem provideKafkaMessage(CommonDataItem commonDataItem) {
        log.info("provide kafka msg:" + commonDataItem);
        kafkaTemplate.send(commonDataItem.topic, commonDataItem).addCallback(new ListenableFutureCallback<SendResult<String, CommonDataItem>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("kafka send fail", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, CommonDataItem> stringCommonDataItemSendResult) {
                RecordMetadata recordMetadata = stringCommonDataItemSendResult.getRecordMetadata();
                log.info("kafka send success partition:{}, offset:{}", recordMetadata.partition(), recordMetadata.offset());
            }
        });
        return commonDataItem;
    }
}
