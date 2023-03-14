package com.codetend.service.txseata.controller;

import com.codetend.common.response.BaseResponse;
import com.codetend.service.txseata.entity.StepTwoEntity;
import com.codetend.service.txseata.services.IServiceSeataTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seata/two")
@BaseResponse
public class ServiceSeataTwoController {
    @Autowired
    private IServiceSeataTwoService serviceSeataTwoService;
    @GetMapping("/getSteps")
    @ResponseBody
    public List<StepTwoEntity> getSteps(){
        return serviceSeataTwoService.getSteps();
    }

    @GetMapping("/getStep/{sid}")
    @ResponseBody
    public StepTwoEntity getStep(@PathVariable("sid") long sid) {
        return serviceSeataTwoService.getStep(sid);
    }

    @PostMapping("/addStep")
    @ResponseBody
    public void addStep(@RequestBody StepTwoEntity stepTwoEntity) {
        serviceSeataTwoService.addStep(stepTwoEntity);
    }

    @PostMapping("/updateStep/{amount}")
    @ResponseBody
    public void updateStep(@RequestBody StepTwoEntity stepTwoEntity, @PathVariable("amount") long amount) {
        serviceSeataTwoService.updateStep(stepTwoEntity, amount);
    }

    @PostMapping("/deleteStep/{sid}")
    @ResponseBody
    public void deleteStep(@PathVariable("sid") long sid) {
        serviceSeataTwoService.deleteStep(sid);
    }
}
