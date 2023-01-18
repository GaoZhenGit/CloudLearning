package com.codetend.common.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = BaseResponse.class)
@Slf4j
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice {

    // 如果接口返回的类型本身就是统一响应体的格式，那就没有必要进行额外的操作，返回true
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
//        log.info("returnType:" + returnType.getParameterType());
//        log.info("converterType:" + converterType);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("content type:" + selectedContentType);
        if (MediaType.APPLICATION_JSON.equals(selectedContentType)) { // 判断响应的Content-Type为JSON格式的body
            if (body instanceof ResponseResult) { // 如果响应返回的对象为统一响应体，则直接返回body
                return body;
            } else {
                // 只有正常返回的结果才会进入这个判断流程，返回正常成功的状态码+信息+数据。
                return new ResponseResult<>(0, "success", body);
            }
        }
        // 非JSON格式body直接返回即可
        return body;
    }
}
