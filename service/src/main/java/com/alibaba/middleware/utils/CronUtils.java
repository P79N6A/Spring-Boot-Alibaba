package com.alibaba.middleware.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Created by lemon on 2018/8/19.
 */
public class CronUtils {
    
    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";
    /***
     *
     * @param date 时间
     * @return  cron类型的日期
     */
    public static String getCron(final Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        String formatTimeStr = "";
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

}
