package com.codetend.common.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommonDataItem {
    public String id;
    public String topic;
    public String tag;
    public String msg;

    public CommonDataItem(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}
