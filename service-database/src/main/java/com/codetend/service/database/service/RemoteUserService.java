package com.codetend.service.database.service;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.ResponseResult;
import com.codetend.service.database.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service-database-user")
public interface RemoteUserService {
    @PostMapping("/database/addUser")
    ResponseResult addUser(@RequestBody User user);
}
