package com.alibaba.middleware.handler.cors;

import com.alibaba.middleware.utils.CorsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支持跨域options post get
 * @author Lemon 2018/6/1 16:48
 */
public class CorsHandlerInterceptor extends HandlerInterceptorAdapter {


    @Value("${cors.allow.origin}")
    private  String projectCorsAllowOrigin;


    public CorsHandlerInterceptor() {
        super();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader("Origin");
        if(StringUtils.isEmpty(projectCorsAllowOrigin)){
            projectCorsAllowOrigin = "*";
        }
        if ((!StringUtils.isEmpty(origin)) && CorsUtils.checkAllow(origin, projectCorsAllowOrigin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent");
        response.setHeader("Access-Control-Expose-Headers","Date");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
