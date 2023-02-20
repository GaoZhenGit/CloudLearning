package com.codetend.dubbo.consumer.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.dubbo.consumer.service.IDubboConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DubboConsumerController {
    @Autowired
    private IDubboConsumerService dubboConsumerService;

    @RequestMapping("dubbo/consumer/remote/{id}")
    @ResponseBody
    public CommonDataItem remote(@PathVariable("id") String id) {
        return dubboConsumerService.remote(id);
    }

    @RequestMapping("dubbo/consumer/kafka/{topic}/{msg}")
    @ResponseBody
    public CommonDataItem provideKafkaMessage(@PathVariable("topic") String topic, @PathVariable("msg") String msg) {
        return dubboConsumerService.provideKafkaMessage(
                CommonDataItem.builder()
                        .topic(topic)
                        .msg(msg)
                        .build());
    }
}
