package com.alibaba.middleware.utils;

/**
 * json封装返回
 * @author Lemon 2018/6/15 11:07
 */
public class AJSON {

    private final static Integer SUCCESS = 200;

    private final static Integer ERROR = 500;

    public static Result SUCCESS(Object object) {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setMsg("success");
        result.setData(object);
        return result;
    }


    public static Result SUCCESS() {
        return SUCCESS(null);
    }

    public static Result ERROR(String msg) {
        Result result = new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }




}
