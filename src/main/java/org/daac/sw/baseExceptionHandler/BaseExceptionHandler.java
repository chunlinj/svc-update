package org.daac.sw.baseExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.daac.sw.entity.Result;
import org.daac.sw.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by  HASEE on 2018-12-07
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        log.error("org.daac.baseExceptionHandler.BaseExceptionHandler.error",e.getMessage());
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
