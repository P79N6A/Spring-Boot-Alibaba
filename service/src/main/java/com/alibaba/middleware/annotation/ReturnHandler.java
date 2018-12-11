package com.alibaba.middleware.annotation;

import java.lang.annotation.*;

/**
 * @author created by lemon.nzg@alibaba-inc.com on 2018/12/7.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnHandler {
}
