package com.codetend.dubbo.consumer.service.impl;

import com.codetend.common.constant.MqTopic;
import com.codetend.common.entity.CommonDataItem;
import com.codetend.dubbo.consumer.service.IDubboConsumerService;
import com.codetend.dubbo.provider.service.DubboMainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DubboConsumerServiceImpl implements IDubboConsumerService {
    @Reference
    private DubboMainService dubboMainService;

    @Override
    public CommonDataItem remote(String id) {
        return dubboMainService.getData();
    }

    @Override
    public CommonDataItem provideKafkaMessage(CommonDataItem commonDataItem) {
        return dubboMainService.provideKafkaMessage(commonDataItem);
    }

    @KafkaListener(
            id = "dubbo-consumer",
            groupId = "kafka-consumer-dubbo",
            topicPartitions = {
                    @TopicPartition(topic = MqTopic.KAFKA_TOPIC, partitions = {"0"})
            }
    )
    public void onKafkaMessage(CommonDataItem commonDataItem) {
        log.info("receive kafka message:" + commonDataItem);
    }

    @KafkaListener(
            id = "dubbo-consumer-side",
            groupId = "kafka-consumer-dubbo-side",
            topics = MqTopic.KAFKA_TOPIC_SIDE
    )
    public void onKafkaMessageSide(CommonDataItem commonDataItem) {
        log.info("receive kafka message:" + commonDataItem);
    }
}
