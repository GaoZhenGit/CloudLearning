package com.codetend.service.provider.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import com.codetend.service.provider.service.IElasticSearchService;
import com.codetend.service.provider.service.IServiceProviderService;
import org.apache.rocketmq.client.producer.SendResult;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/es")
@BaseResponse
public class ServiceElasticSearchController {
    @Autowired
    IElasticSearchService elasticSearchService;

    @RequestMapping("/add/{index}")
    @ResponseBody
    public String add(
            @PathVariable("index") String index,
            @RequestBody CommonDataItem commonDataItem) {
        return elasticSearchService.add(index, commonDataItem);
    }

    @RequestMapping("/getAll/{index}/{id}")
    @ResponseBody
    public List<Map<?,?>> getAll(
            @PathVariable("index") String index,
            @PathVariable("id") String id) {
        return elasticSearchService.getAll(index, id);
    }

    @RequestMapping("/termQuery/{index}/{keywords}")
    @ResponseBody
    public List<Map<?,?>> termQuery(
            @PathVariable("index") String index,
            @PathVariable("keywords") String keywords) {
        return elasticSearchService.termQuery(index, keywords);
    }

    @RequestMapping("/match/{index}/{field}/{key}")
    @ResponseBody
    public List<Map<?,?>> termQuery(
            @PathVariable("index") String index,
            @PathVariable("field") String field,
            @PathVariable("key") String key) {
        return elasticSearchService.matchPhraseQuery(index, field, key);
    }
}
