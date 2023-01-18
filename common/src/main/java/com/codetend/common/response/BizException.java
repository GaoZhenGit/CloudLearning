package com.codetend.common.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BizException extends RuntimeException{
    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    private int code;
    private String msg;
}
