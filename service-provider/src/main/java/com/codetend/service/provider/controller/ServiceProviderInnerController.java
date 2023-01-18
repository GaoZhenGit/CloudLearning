package com.codetend.service.provider.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import com.codetend.service.provider.service.IServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/provider")
public class ServiceProviderInnerController {
    @Autowired
    IServiceProviderService serviceProviderService;

    @RequestMapping("/inner-test/{id}")
    @ResponseBody
    public CommonDataItem testInner(@PathVariable("id") String id) {
        return serviceProviderService.test(id);
    }
}
