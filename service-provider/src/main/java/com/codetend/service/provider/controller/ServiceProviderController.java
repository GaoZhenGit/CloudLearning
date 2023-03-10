package com.codetend.service.provider.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import com.codetend.service.provider.service.IServiceProviderService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/provider")
@BaseResponse
public class ServiceProviderController {
    @Autowired
    IServiceProviderService serviceProviderService;

    @RequestMapping("/test/{id}")
    @ResponseBody
    public CommonDataItem test(@PathVariable("id") String id) {
        return serviceProviderService.test(id);
    }

    @PostMapping(value = "/channel")
    @ResponseBody
    public CommonDataItem sendClusterMsg(
            @RequestParam("topic") String topic,
            @RequestParam("message") String message) {
        return serviceProviderService.sendClusterMsgByChannel(topic, message);
    }

    @PostMapping(value = "/bridge")
    @ResponseBody
    public CommonDataItem sendBridgeMsg(
            @RequestParam("topic") String topic,
            @RequestParam("message") String message) {
        return serviceProviderService.sendClusterMsgByBridge(topic, message);
    }

    @PostMapping(value = "/native")
    @ResponseBody
    public SendResult sendNativeMsg(
            @RequestParam("tag") String tag,
            @RequestParam("topic") String topic,
            @RequestParam("message") String message) {
        return serviceProviderService.sendClusterMsgByNative(topic, tag, message);
    }
}
