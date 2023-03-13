package com.codetend.service.txseata.controller;

import com.codetend.common.response.BaseResponse;
import com.codetend.service.txseata.entity.FullStep;
import com.codetend.service.txseata.services.ISeataMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seata/main")
@BaseResponse
public class ServiceSeataMainController {
    @Autowired
    private ISeataMainService seataMainService;

    @PostMapping("/addStep")
    @ResponseBody
    public void addDoubleSteps(@RequestBody FullStep fullStep) {
        seataMainService.addDoubleSteps(fullStep);
    }
}
