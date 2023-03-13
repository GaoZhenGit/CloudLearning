package com.codetend.service.txseata.services;

import com.codetend.common.response.ResponseResult;
import com.codetend.service.txseata.entity.StepTwoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("seata-two/seata/two")
public interface FeignTwoService {
    @GetMapping("/getSteps")
    @ResponseBody
    ResponseResult<List<StepTwoEntity>> getSteps();

    @GetMapping("/getStep/{sid}")
    @ResponseBody
    ResponseResult<StepTwoEntity> getStep(@PathVariable("sid") long sid);

    @PostMapping("/addStep")
    @ResponseBody
    ResponseResult<Void> addStep(@RequestBody StepTwoEntity stepTwoEntity);

    @PostMapping("/deleteStep/{sid}")
    @ResponseBody
    ResponseResult<Void> deleteStep(@PathVariable("sid") long sid);
}
