package com.codetend.dubbo.provider.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.dubbo.provider.service.DubboMainService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DubboProviderController {
    @Reference
    private DubboMainService dubboMainService;
    @RequestMapping("dubbo/provider/kafka/{topic}/{msg}")
    @ResponseBody
    public CommonDataItem provideKafkaMessage(@PathVariable("topic") String topic, @PathVariable("msg") String msg) {
        return dubboMainService.provideKafkaMessage(
                CommonDataItem.builder()
                        .topic(topic)
                        .msg(msg)
                        .build());
    }
}
