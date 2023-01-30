package com.codetend.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {
    /**
     * 返回的状态码
     */
    private int code;
    /**
     * 返回的信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;
}
