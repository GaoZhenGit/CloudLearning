package com.codetend.datasource.multi.controller;

import com.codetend.common.response.BaseResponse;
import com.codetend.datasource.multi.entity.User;
import com.codetend.datasource.multi.service.IMultiSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/multi-source")
@BaseResponse
public class MultiSourceController {
    @Autowired
    private IMultiSourceService multiSourceService;
    @GetMapping("/master/users")
    @ResponseBody
    public List<User> getUsersMaster() {
        return multiSourceService.getUsersMaster();
    }
    @GetMapping("/second/users")
    @ResponseBody
    public List<User> getUsersSecond() {
        return multiSourceService.getUsersSecond();
    }
}
