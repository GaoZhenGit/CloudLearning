package com.codetend.service.database.service;

import com.codetend.common.response.ResponseResult;
import com.codetend.service.database.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "service-database-order")
public interface RemoteOrderService {
    @PostMapping("/database/addOrder")
    ResponseResult addOrder(@RequestBody Order order);
}
