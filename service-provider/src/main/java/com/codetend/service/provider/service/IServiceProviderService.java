package com.codetend.service.provider.service;

import com.codetend.common.entity.CommonDataItem;
import org.springframework.web.bind.annotation.RequestParam;

public interface IServiceProviderService {
    CommonDataItem test(String id);
    CommonDataItem sendClusterMsg(String topic, String message);
}
