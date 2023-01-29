package com.codetend.service.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1)
@Component
public class AuthFilter implements GlobalFilter {
    private static final String COOKIE_AUTH_KEY = "auth";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
//        MultiValueMap<String, String> params = request.getQueryParams();
        // 2.获取参数中的 authorization 参数
        HttpCookie authCookie = cookies.getFirst(COOKIE_AUTH_KEY);
        if (authCookie != null) {
            String authValue = authCookie.getValue();
            if ("admin".equals(authValue)) {
                return chain.filter(exchange);
            }
        }
        // 5.否，拦截
        // 5.1.设置状态码 这里设置的状态码是没有认证（登录）
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().addCookie(
                ResponseCookie.from(COOKIE_AUTH_KEY, "admin")
                        .path("/")
                        .build()
        );
        // 5.2.拦截请求
        return exchange.getResponse().setComplete();
    }
}
