package com.alibaba.middleware.exp;

/**
 * 业务逻辑层异常
 * @author Created by lemon on 2018/8/13.
 */
public class BizException extends RuntimeException {

    public BizException(){super();}

    public BizException(String msg){super(msg);}

}
