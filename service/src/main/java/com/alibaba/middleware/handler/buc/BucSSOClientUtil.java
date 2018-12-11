package com.alibaba.middleware.handler.buc;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类BucSSOClientUtil.java的实现描述：TODO 类实现描述
 * 
 * @author howard 22 Mar, 2012 8:28:12 AM
 */
@Component
public class BucSSOClientUtil {
	
	@Autowired
	static WebApplicationContext webApplicationConnect;

    private static Logger logger = LoggerFactory.getLogger(BucSSOClientUtil.class);
    
    public static final String SESSION_LOGIN_KEY = "LOGIN_USER";

    public static void handleSSOLogin(String ssoToken, HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        /*TokenUserDTO dto = null;
        try {
            dto = BucSSOUtil.renewToken(FilterManager.getSsoTokenApi(), ssoToken, FilterManager.getAppCode(), true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (dto != null && StringUtils.isNotBlank(dto.getToken())) {
        	//检查token是否是对应访问该应用生成的
        	if(StringUtils.isBlank(dto.getAppName())||!dto.getAppName().equals(FilterManager.getAppName())){
        		StringBuilder builder = new StringBuilder();
        		builder.append("renewToken error,token:").append(dto.getToken()).append(",login appName:").append(FilterManager.getAppName()).append(",token appName:").append(dto.getAppName());
        		sendRedirect(response, String.format(FilterManager.getErrorUrl(), BucSSOConstants.ERROR_1402, builder.toString()));
        		return ;
        	}
            // 执行登录成功后的回调接口
        	request.getSession().setAttribute(SESSION_LOGIN_KEY, dto.getUser());
        	request.getSession().setMaxInactiveInterval(2592000);
            if (!response.isCommitted()) {
                try {
                    response.setHeader("P3P",
                                       "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                // 执行登录成功后的回调接口
            }

        } else {
        	request.getSession().removeAttribute(SESSION_LOGIN_KEY);
        }*/
    }

    public static void handleSSOLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("P3P", "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
        req.getSession().removeAttribute(SESSION_LOGIN_KEY);
    }
    
    public static void sendRedirect(ServletResponse servletResponse, String redirectUrl) throws IOException {
    	HttpServletResponse response = (HttpServletResponse)servletResponse;
        JSONObject outputMSg = new JSONObject();
        outputMSg.put("code", 302);
        outputMSg.put("location", redirectUrl);
        ServletOutputStreamUtil.output(outputMSg, response, FilterManager.getAccessControlAllowOrigin());
    }
}
