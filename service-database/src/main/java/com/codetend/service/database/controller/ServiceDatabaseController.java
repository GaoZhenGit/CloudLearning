package com.codetend.service.database.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/database")
@BaseResponse
public class ServiceDatabaseController {
    @RequestMapping("/test/{id}")
    @ResponseBody
    public CommonDataItem test(@PathVariable("id") String id) {
        return CommonDataItem.builder().id(id).build();
    }
}
