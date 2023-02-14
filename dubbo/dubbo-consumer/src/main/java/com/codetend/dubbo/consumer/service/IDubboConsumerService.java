package com.codetend.dubbo.consumer.service;

import com.codetend.common.entity.CommonDataItem;
import org.springframework.web.bind.annotation.PathVariable;

public interface IDubboConsumerService {
    CommonDataItem remote(String id);
}
