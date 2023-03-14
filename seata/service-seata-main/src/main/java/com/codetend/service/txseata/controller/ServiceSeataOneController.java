package com.codetend.service.txseata.controller;

import com.codetend.common.response.BaseResponse;
import com.codetend.service.txseata.entity.StepOneEntity;
import com.codetend.service.txseata.services.IServiceSeataOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seata/one")
@BaseResponse
public class ServiceSeataOneController {
    @Autowired
    private IServiceSeataOneService serviceSeataOneService;
    @GetMapping("/getSteps")
    @ResponseBody
    public List<StepOneEntity> getSteps(){
        return serviceSeataOneService.getSteps();
    }

    @GetMapping("/getStep/{sid}")
    @ResponseBody
    public StepOneEntity getStep(@PathVariable("sid") long sid) {
        return serviceSeataOneService.getStep(sid);
    }

    @PostMapping("/addStep")
    @ResponseBody
    public void addStep(@RequestBody StepOneEntity stepOneEntity) {
        serviceSeataOneService.addStep(stepOneEntity);
    }

    @PostMapping("/updateStep/{amount}")
    @ResponseBody
    public void updateStep(@RequestBody StepOneEntity stepOneEntity, @PathVariable("amount") long amount) {
        serviceSeataOneService.updateStep(stepOneEntity, amount);
    }

    @PostMapping("/deleteStep/{sid}")
    @ResponseBody
    public void deleteStep(@PathVariable("sid") long sid) {
        serviceSeataOneService.deleteStep(sid);
    }
}
