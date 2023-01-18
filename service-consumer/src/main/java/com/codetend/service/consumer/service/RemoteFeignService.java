package com.codetend.service.consumer.service;

import com.codetend.common.entity.CommonDataItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "service-provider")
public interface RemoteFeignService {
    @RequestMapping("/provider/inner-test/{id}")
    CommonDataItem test(@PathVariable("id") String id);
}
