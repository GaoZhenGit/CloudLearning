package com.codetend.dubbo.provider.utils;

import com.codetend.common.entity.CommonDataItem;
import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CommonDataSerializer implements Serializer<CommonDataItem> {
    private Gson gson = new Gson();
    @Override
    public byte[] serialize(String s, CommonDataItem commonDataItem) {
        return gson.toJson(commonDataItem).getBytes();
    }
}
