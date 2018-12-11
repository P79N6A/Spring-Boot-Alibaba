package com.alibaba.middleware.container.mycomponent;

import com.alibaba.middleware.policy.random.IRandomConfigValuePolicy;
import com.alibaba.middleware.policy.random.RandomNamePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 电脑名字随机
 * @author Created by lemon.nzg@alibaba-inc.com on 2018/8/15.
 */
@Component
public class RandomNameComponent implements IRandomConfigValuePolicy {

    @Value("${cmp.ugc.local.random.nickname:''}")
    private String filePath;

    private RandomNamePolicy policy;

    @Override
    public String get() {
        if(policy == null){
            policy = new RandomNamePolicy(filePath);
        }
        return policy.get();
    }
}
