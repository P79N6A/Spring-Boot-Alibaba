package com.alibaba.middleware.annotation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author created by lemon.nzg@alibaba-inc.com on 2018/12/7.
 */
@Configuration
public class ResponseBodyWrapFactory implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter adapter;


    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        //增加一个自定义的属性
        for(int i = 0;i<handlers.size();i++){
            HandlerMethodReturnValueHandler handler = handlers.get(i);
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                JsonReturnValueHandler decorator = new JsonReturnValueHandler(handler);
                handlers.add(i, decorator);
                break;
            }
        }
    }
}
