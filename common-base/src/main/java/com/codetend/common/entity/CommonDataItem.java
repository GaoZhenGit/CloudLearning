package com.codetend.common.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommonDataItem implements Serializable {
    public String id;
    public String topic;
    public String tag;
    public String msg;

    public CommonDataItem(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}
