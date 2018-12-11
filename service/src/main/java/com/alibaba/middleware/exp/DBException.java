package com.alibaba.middleware.exp;

/**
 * DB层异常
 * @author Created by lemon on 2018/8/13.
 */
public class DBException extends RuntimeException{

    public DBException(){super();}

    public DBException(String msg){super(msg);}

}
