package com.alibaba.middleware.exp;

/**
 * 校验判断层异常
 * @author Created by lemon on 2018/8/13.
 */
public class CheckedException extends RuntimeException {

    public CheckedException(){super();}

    public CheckedException(String msg){super(msg);}

}
