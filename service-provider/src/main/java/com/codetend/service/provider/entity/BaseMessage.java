package com.codetend.service.provider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@AllArgsConstructor
@ToString
public class BaseMessage<D> {
    /**
     * 消息主题
     */
    private String topic;
    /**
     * 消息标签
     */
    private String tag;
    /**
     * 消息内容
     */
    private D data;
    /**
     *
     */
    private Map<String, Object> header;

    public BaseMessage(String topic, String tag, D data) {
        this.topic = topic;
        this.tag = tag;
        this.data = data;
    }
}
