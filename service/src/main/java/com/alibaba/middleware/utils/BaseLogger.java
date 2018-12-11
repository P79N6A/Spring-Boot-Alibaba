package com.alibaba.middleware.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 
 * 接口或类的说明
 *
 * <br>==========================
 * <br> 公司：优视科技
 * <br> 开发：<a href="mailto:zhangxx2@ucweb.com">张宪新</a>
 * <br> 版本：1.0
 * <br> 创建时间：2013-12-26
 * <br>==========================
 */
class BaseLogger {

    public final static Logger logger = LoggerFactory.getLogger(BaseLogger.class);
    /**
     * 格式化消息内容
     * @param msg
     * @param params
     * @return
     */
    protected static String compileMsg(String msg, String... params) {
        if (params == null || params.length <= 0) {
            return msg;
        }
        Object[] args = new Object[params.length];
        try {
            args = Arrays.copyOf(params, args.length);
            return String.format(msg, args);
        } catch (Exception e) {
            logger.error(msg, e);
            return msg;
        } finally {
            args = null;
        }
    }

}
