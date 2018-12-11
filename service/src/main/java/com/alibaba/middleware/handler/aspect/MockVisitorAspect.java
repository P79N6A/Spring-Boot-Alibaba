package com.alibaba.middleware.handler.aspect;

import com.alibaba.middleware.exp.CheckedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP 请求拦截业务
 * @author Lemon 2018/5/15 17:37
 */
@Aspect
@Order(1)
@Component
public class MockVisitorAspect {

    @Value("${cors.allow.url}")
    private String UC_CLIENT_CHECK_URL_ALL;

    /**
     *
     * public * com.alibaba.middleware.AppController.*(..)
     * public 返回值 路径.类.方法（参数）
     * public Object com.alibaba.middleware.AppController.checkPreload() 命中
     */
    @Pointcut("execution(public * com.alibaba.middleware.AppController.*(..))")
    public void webPath() {

    }

    @Before("webPath()")
    public void beforeMockUser(JoinPoint point){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(checkClientUrl(attributes.getRequest())){
            throw new CheckedException("您没有权限访问，请联系管理员");
        }
        //这里处理请求拦截业务逻辑...
    }


    @AfterReturning(returning = "ret", pointcut = "webPath()")
    public void doAfterReturning(Object ret) {
    }

    /**
     * 判断是否进行客户端检查
     *
     * @return
     */
    public boolean checkClientUrl(HttpServletRequest request) {
        String config = UC_CLIENT_CHECK_URL_ALL;
        if (StringUtils.isEmpty(config)) {
            return false;
        }
        String path = request.getServletPath();
        for (String str : config.split("\\,")) {
            if (path.equals(str.trim())) {
                return true;
            }
        }
        return false;
    }

}
