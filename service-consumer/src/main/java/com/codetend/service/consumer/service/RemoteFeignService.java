package com.codetend.service.consumer.service;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.ResponseResult;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-provider")
public interface RemoteFeignService {
    @RequestMapping("/provider/inner-test/{id}")
    CommonDataItem innerTest(@PathVariable("id") String id);

    @RequestMapping("/provider/test/{id}")
    ResponseResult<CommonDataItem> test(@PathVariable("id") String id);

    @PostMapping(value = "/provider/native")
    @ResponseBody
    ResponseResult<SendResult> sendNativeMsg(
            @RequestParam("tag") String tag,
            @RequestParam("topic") String topic,
            @RequestParam("message") String message);
}
