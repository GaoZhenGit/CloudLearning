package com.codetend.service.provider.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import com.codetend.service.provider.service.IServiceProviderService;
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

    @PostMapping(value = "/cluster")
    @ResponseBody
    public CommonDataItem sendClusterMsg(
            @RequestParam("topic") String topic,
            @RequestParam("message") String message) {
        return serviceProviderService.sendClusterMsg(topic, message);
    }
}
