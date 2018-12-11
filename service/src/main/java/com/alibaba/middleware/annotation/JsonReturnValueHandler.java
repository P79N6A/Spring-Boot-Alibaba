package com.alibaba.middleware.annotation;

import com.alibaba.middleware.utils.AJSON;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author created by lemon.nzg@alibaba-inc.com on 2018/12/7.
 */
public class JsonReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;

    public JsonReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getMethodAnnotation(ReturnHandler.class) != null || methodParameter.getMethodAnnotation(ResponseBody.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Class<?> returnParaType = returnType.getParameterType();
        if (returnType.getMethodAnnotation(ReturnHandler.class) != null) {
            if (!void.class.isAssignableFrom(returnParaType)) {
                //包装一层
                delegate.handleReturnValue(AJSON.SUCCESS(returnValue), returnType, modelAndViewContainer, nativeWebRequest);
            }else {
                //不包装
                delegate.handleReturnValue(AJSON.SUCCESS(), returnType, modelAndViewContainer, nativeWebRequest);
            }

        }
    }
}
