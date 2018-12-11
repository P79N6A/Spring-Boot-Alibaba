package com.alibaba.middleware.policy.ugc.sensitive;

/**
 * Created by ellison on 2017/10/19.
 */
public interface IInputContentCheckingPolicy {

    boolean hasSensitive(String content);
}
