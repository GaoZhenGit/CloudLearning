package com.codetend.common.controllers;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
@BaseResponse
public class CommonController {
    @RequestMapping("/test/{id}")
    @ResponseBody
    public CommonDataItem test(@PathVariable("id") String id) {
        return new CommonDataItem(id, "success");
    }
}
