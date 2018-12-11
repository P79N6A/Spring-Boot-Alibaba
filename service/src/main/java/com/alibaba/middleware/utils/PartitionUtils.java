package com.alibaba.middleware.utils;

/**
 * 分库分表
 * Created by ellison on 2017/12/27.
 */
public class PartitionUtils {

    public static int hash(String s) {
        int h;
        if (s == null || "".equals(s.trim())) {
            h = 0;
        } else {
            h = s.hashCode() % 52;
        }

        if (h < 0) {
            h = -h;
        }
        LogsClick.log("s=" + s + ", hash=" + h);

        return h;
    }

    public static int hash100(String s) {
        int h;
        if (s == null || "".equals(s.trim())) {
            h = 0;
        } else {
            h = s.hashCode() % 100;
        }

        if (h < 0) {
            h = -h;
        }

        LogsClick.log("s=" + s + ", hash=" + h);

        return h;
    }
}
