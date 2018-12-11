package com.alibaba.middleware.handler.controller;

import com.alibaba.middleware.exp.BizException;
import com.alibaba.middleware.exp.CheckedException;
import com.alibaba.middleware.exp.DBException;
import com.alibaba.middleware.utils.AJSON;
import com.alibaba.middleware.utils.LogsClick;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述: 统一异常处理
 * @author Lemon 2018/6/15 13:42
 */
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    /**
     * 校验层异常拦截
     *
     * @return error
     */
    @ExceptionHandler(CheckedException.class)
    public Object handleCheckedException(CheckedException e) {
        LogsClick.logException("CheckedException："+e.toString(),e);
        return AJSON.ERROR(e.toString());
    }


    /**
     * 业务层异常拦截
     *
     * @return error
     */
    @ExceptionHandler(BizException.class)
    public Object handleBizException(BizException e) {
        LogsClick.logException("BizException："+e.toString(),e);
        return AJSON.ERROR(e.toString());
    }


    /**
     * DB异常拦截
     *
     * @return error
     */
    @ExceptionHandler(DBException.class)
    public Object handleDBException(DBException e) {
        LogsClick.logException("DBException："+e.toString(),e);
        return AJSON.ERROR(e.toString());
    }


    /**
     * 功能描述: 通常最后异常拦截
     *
     * @return error
     */
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        LogsClick.logException("Exception："+e.toString(),e);
        return AJSON.ERROR(e.toString());
    }

}
