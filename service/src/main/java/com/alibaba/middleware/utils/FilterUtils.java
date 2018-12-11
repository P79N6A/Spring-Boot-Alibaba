package com.alibaba.middleware.utils;

/**
 * @author Created by lemon on 2018/8/14.
 */
public class FilterUtils {
    /**
     * 过滤html
     *
     * @param content
     * @return
     */
    private static String filterHtml (String content) {
        if (content == null || content.isEmpty()) {
            return content;
        }

        return content.replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

}
