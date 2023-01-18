package com.codetend.service.consumer.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import com.codetend.service.consumer.service.IServiceConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
