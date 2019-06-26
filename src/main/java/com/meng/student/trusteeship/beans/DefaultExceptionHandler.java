package com.meng.student.trusteeship.beans;

import com.meng.student.trusteeship.entity.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengqigui
 * @description 统一异常处理类
 * @date 2018/03/21 16:54
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseResult producerExceptionHandler(NativeWebRequest request, Exception exception) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request.getNativeRequest();
        //  获取请求的Url
        String servletPath = httpServletRequest.getServletPath();
        LOGGER.error("请求\"{}\"URL发生错误：", servletPath, exception);
        return new BaseResult(exception);

    }


}
