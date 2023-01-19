package com.codetend.service.consumer.service.impl;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.service.consumer.service.IServiceConsumerService;
import com.codetend.service.consumer.service.MySink;
import com.codetend.service.consumer.service.RemoteFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.function.Consumer;

@Service
@Slf4j
@EnableBinding(MySink.class)
public class ServiceConsumerServiceImpl implements IServiceConsumerService {
    @Resource
    private RemoteFeignService remoteFeignService;
    @Value("${server.port}")
    private int port;
//    @Autowired
//    private MySink sink;
    @Override
    public CommonDataItem getRemoteData(String id) {
        return remoteFeignService.test(id);
    }

//    @Bean
//    public Consumer<CommonDataItem> consumer3() {
//        return new Consumer<CommonDataItem>() {
//            @Override
//            public void accept(CommonDataItem item) {
//                log.info("接收的集群消息3为：" + item);
//            }
//        };
//    }

    @StreamListener(MySink.INPUT1)
    public void receive1(CommonDataItem item) {
        log.info(port + "接受消息1：" + item);
    }

    @StreamListener(MySink.INPUT1)
    public void receive1_1(CommonDataItem item) {
        log.info(port + "接受消息1_1：" + item);
    }

    @StreamListener(MySink.INPUT2)
    public void receive2(CommonDataItem item) {
        log.info(port + "接受消息2：" + item);
    }

    @StreamListener(MySink.INPUT3)
    public void receive3(CommonDataItem item) {
        log.info(port + "接受消息3：" + item);
    }
}
