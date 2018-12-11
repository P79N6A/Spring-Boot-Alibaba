package com.alibaba.middleware.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.middleware.exp.BizException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Author: Lemon 2018/7/16 10:05
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject doGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        String result = null;
        try {
            apiUrl += param;
            if(apiUrl.contains("https://")){
                result = https(apiUrl);
            }else{
                HttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(apiUrl);
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new BizException(e.getMessage());
        }
        return JSON.parseObject(result);
    }

    public static String https(String https) throws IOException {
        String mt;
        HttpsURLConnection conn = (HttpsURLConnection) new URL(https).openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        mt = sb.toString();

        return mt;
    }

    public static JSONObject post(String url,Map<String,String> paramMap){
        String result = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            // 设置超时时间
            HttpPost post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            // 构建消息实体
            StringEntity entity = new StringEntity(JSON.toJSONString(paramMap), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
            post.setConfig(requestConfig);

            HttpResponse response = httpClient.execute(post);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, "UTF-8");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new BizException(e.getMessage());
        }
        return JSON.parseObject(result);
    }


}
