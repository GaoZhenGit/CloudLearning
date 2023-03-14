package com.codetend.service.txseata.services;

import com.codetend.common.response.ResponseResult;
import com.codetend.service.txseata.entity.StepOneEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("seata-one/seata/one")
public interface FeignOneService {
    @GetMapping("/getSteps")
    @ResponseBody
    ResponseResult<List<StepOneEntity>> getSteps();

    @GetMapping("/getStep/{sid}")
    @ResponseBody
    ResponseResult<StepOneEntity> getStep(@PathVariable("sid") long sid);

    @PostMapping("/addStep")
    @ResponseBody
    ResponseResult<Void> addStep(@RequestBody StepOneEntity stepOneEntity);

    @PostMapping("/updateStep/{amount}")
    @ResponseBody
    ResponseResult<Void> updateStep(@RequestBody StepOneEntity stepOneEntity, @PathVariable("amount") long amount);

    @PostMapping("/deleteStep/{sid}")
    @ResponseBody
    ResponseResult<?> deleteStep(@PathVariable("sid") long sid);
}
