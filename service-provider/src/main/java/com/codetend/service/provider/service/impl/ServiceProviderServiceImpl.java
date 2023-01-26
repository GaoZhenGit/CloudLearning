package com.codetend.service.provider.service.impl;

import com.codetend.common.constant.RocketMqTopic;
import com.codetend.common.entity.CommonDataItem;
import com.codetend.service.provider.service.IServiceProviderService;
import com.codetend.service.provider.service.MySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
@EnableBinding(MySource.class)
public class ServiceProviderServiceImpl implements IServiceProviderService {
    @Value("${spring.application.name}")
    private String moduleName;
    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private MySource source;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public CommonDataItem test(String id) {
        return new CommonDataItem(id, String.format("data from:%s", moduleName));
    }

    @Override
    public CommonDataItem sendClusterMsgByChannel(String topic, String message) {
//        Message<CommonDataItem> msg = new GenericMessage<>(new CommonDataItem("id:" + topic, message));
//        boolean result = streamBridge.send(topic, msg);
//        log.info("消息集群发送: " + msg.getPayload());
        MessageBuilder<CommonDataItem> builder = MessageBuilder.withPayload(new CommonDataItem("id:" + topic, message))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("topic", topic);
        Message<CommonDataItem> msg = builder.build();
        boolean result;
        switch (topic) {
            default:
            case "input1":
                result = source.output1().send(msg);
                break;
            case "input2":
                result = source.output2().send(msg);
                break;
            case "input3":
                result = source.output3().send(msg);
                break;
        }
        return new CommonDataItem(result ? "success" : "fail", msg.getPayload().toString());
    }

    @Override
    public CommonDataItem sendClusterMsgByBridge(String topic, String message) {
        Message<CommonDataItem> msg = new GenericMessage<>(new CommonDataItem("id:" + topic, message));
        boolean result = streamBridge.send(topic, msg);
        return new CommonDataItem(result ? "success" : "fail", msg.getPayload().toString());
    }

    @Override
    public SendResult sendClusterMsgByNative(String topic, String tag, String message) {
        CommonDataItem item = CommonDataItem.builder()
                .id(topic)
                .topic(topic)
                .tag(tag)
                .msg(message)
                .build();
        return rocketMQTemplate.syncSend(topic+":"+tag, item);
    }
}
