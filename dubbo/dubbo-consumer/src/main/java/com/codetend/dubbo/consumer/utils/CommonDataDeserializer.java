package com.codetend.dubbo.consumer.utils;

import com.codetend.common.entity.CommonDataItem;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CommonDataDeserializer implements Deserializer<CommonDataItem> {
    private Gson gson = new Gson();
    @Override
    public CommonDataItem deserialize(String s, byte[] bytes) {
        String json = new String(bytes);
        return gson.fromJson(json, CommonDataItem.class);
    }
}
