package com.codetend.service.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class TimeGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeGatewayFilterFactory.Config> {
    private static final String BEGIN_TIME = "beginTime";
    public TimeGatewayFilterFactory() {
        super(TimeGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("show");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                exchange.getAttributes().put(BEGIN_TIME, System.currentTimeMillis());
                /**
                 *  pre的逻辑
                 * chain.filter().then(Mono.fromRunable(()->{
                 *     post的逻辑
                 * }))
                 */
                log.info("gateway config:" + config);
                return chain.filter(exchange).then(Mono.fromRunnable(()->{
                    Long startTime = exchange.getAttribute(BEGIN_TIME);
                    if (startTime != null) {
                        log.info("{}请求耗时：{}", exchange.getRequest().getURI(), System.currentTimeMillis() - startTime);
                    }
                }));
            }
        };
    }
    @Setter
    @Getter
    @ToString
    static class Config{
        private String show;
    }
}
