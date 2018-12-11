package com.alibaba.middleware.utils;

/**
 * Created by ellison on 2017/12/22.
 */
public class CorsUtils {

    public static boolean checkAllow(String origin, String config) {
        if ("".equals(config)) {
            return false;
        }

        if ("*".equals(config)) {
            return true;
        }

        String[] arr = config.split("\\,");
        for (String a : arr) {
            a = a.trim();
            if (a.equals(origin)) {
                return true;
            }

            // regexp
            if (origin.matches(a)) {
                return true;
            }
        }

        return false;
    }


}
