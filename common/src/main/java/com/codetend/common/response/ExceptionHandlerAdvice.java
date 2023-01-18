package com.codetend.common.response;


import com.codetend.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 处理未捕获的Exception
     *
     * @param e 异常
     * @return 统一响应体
     * data:null
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<Exception> handleException(Exception e) {
        log.error(e.getMessage(), e);
        log.info("======API end:"+ RequestUtil.getCurrentUrl() + "=====");
        return new ResponseResult<>(-500, e.getMessage(), null);
    }

    /**
     * 处理未捕获的RuntimeException
     *
     * @param e 运行异常
     * @return 统一响应体
     * data:null
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<RuntimeException> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        log.info("======API end:"+ RequestUtil.getCurrentUrl() + "=====");
        return new ResponseResult<>(-500, e.getMessage(), null);
    }

    /**
     * 处理业务异常BaseException
     *
     * @param e 业务异常
     * @return 统一响应体
     * data:null
     */
    @ExceptionHandler(BizException.class)
    public ResponseResult<String> handleBaseException(BizException e) {
        log.error(e.getMessage(), e);
        log.info("======API end:"+ RequestUtil.getCurrentUrl() + "=====");
        return new ResponseResult<>(e.getCode(), e.getMsg(), e.getMessage());
    }
}