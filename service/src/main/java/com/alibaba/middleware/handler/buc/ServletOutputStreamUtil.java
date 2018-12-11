package com.alibaba.middleware.handler.buc;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ServletOutputStream将数据返回给客户端工具类
 */
public class ServletOutputStreamUtil {

    /**
     * 通过ServletOutputStream将数据返回给客户端
     * @param outputMSg                输出的数据信息
     * @param response                 response对象
     * @param accessControlAllowOrigin 允许写入前段cookie的Origin
     * @throws IOException IO异常
     */
    public static void output(JSONObject outputMSg, HttpServletResponse response, String accessControlAllowOrigin)
        throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(outputMSg.toJSONString().getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}