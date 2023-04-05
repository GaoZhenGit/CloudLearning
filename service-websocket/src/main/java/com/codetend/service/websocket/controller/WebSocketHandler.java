package com.codetend.service.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ws消息处理类
 */
@Component
@Slf4j
public class WebSocketHandler extends AbstractWebSocketHandler {
    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        log.info("建立ws连接{}", session.getId());
        sessionMap.put(session.getId(),session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        log.info("发送文本消息");
        // 获得客户端传来的消息
        String payload = message.getPayload();
        log.info("接收到{}消息:{}",session.getId(), payload);

//        session.sendMessage(new TextMessage("server 发送给的消息 " + payload + "，发送时间:" + LocalDateTime.now().toString()));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        log.info("发送二进制消息");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("异常处理");
        session.close();
        sessionMap.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("关闭ws连接");
        sessionMap.remove(session.getId());
    }


    @Scheduled(cron ="*/10 * * * * ?")
    public void sayHello() {
        for (WebSocketSession session: sessionMap.values()) {
            try {
                TextMessage textMessage = new TextMessage(String.format("系统自动消息：%s", System.currentTimeMillis()));
                session.sendMessage(textMessage);
                log.info("自动消息发送:{}", textMessage.getPayload());
            } catch (Exception e) {
                log.error("自动消息发送失败{}", e.getMessage(), e);
            }
        }
    }
}

