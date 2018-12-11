package com.alibaba.middleware.utils;

/**
 * 日志打点
 * @author Lemon 2018/7/27 14:22
 */
public class LogsClick extends BaseLogger{

    public static void click(String tag, String msg) {
        logger.info(tag + " --- " + msg + "\n");
    }

    public static void log(String msg) { logger.info(msg); }

    public static void log(String msg, String... params) {
        logger.info(compileMsg(msg, params));
    }

    public static void logException(String msg, Exception e) { logger.error(msg, e); }

    public static void logException(String msg, String... params) {
        logger.error(compileMsg(msg, params));
    }


}
