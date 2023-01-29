package com.codetend.service.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TimeGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeGatewayFilterFactory.Config> {
    private static final String BEGIN_TIME = "beginTime";
    public TimeGatewayFilterFactory() {
        super(TimeGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.stream(Config.class.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(BEGIN_TIME, System.currentTimeMillis());
            /*
             *  pre的逻辑
             * chain.filter().then(Mono.fromRunable(()->{
             *     post的逻辑
             * }))
             */
            log.info("gateway config:" + config);
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                Long startTime = exchange.getAttribute(BEGIN_TIME);
                if (startTime != null) {
                    long cost = System.currentTimeMillis() - startTime;
                    if (cost > config.threshold) {
                        log.warn("{}请求耗时：{}, 阈值:{}", exchange.getRequest().getURI(), cost, config.threshold);
                    }
                }
            }));
        };
    }
    @Setter
    @Getter
    @ToString
    static class Config{
        public String show;
        public long threshold;
    }
}
