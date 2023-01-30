package com.codetend.service.consumer.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import com.codetend.common.response.ResponseResult;
import com.codetend.service.consumer.service.IServiceConsumerService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consumer")
@BaseResponse
public class ServiceConsumerController {
    @Autowired
    private IServiceConsumerService consumerService;
    @RequestMapping("/test/{id}")
    @ResponseBody
    public CommonDataItem test(@PathVariable("id") String id) {
        return consumerService.getRemoteData(id);
    }

    @PostMapping(value = "/native")
    @ResponseBody
    public SendResult sendNativeMsg(
            @RequestParam("tag") String tag,
            @RequestParam("topic") String topic,
            @RequestParam("message") String message){
        return consumerService.sendNativeMsg(tag, topic, message);
    };
}
