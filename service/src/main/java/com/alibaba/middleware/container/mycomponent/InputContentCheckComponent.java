package com.alibaba.middleware.container.mycomponent;

import com.alibaba.middleware.policy.ugc.processor.CompleteMatchSensitiveWordsProcessor;
import com.alibaba.middleware.policy.ugc.processor.PartSensitiveWordsProcessor;
import com.alibaba.middleware.policy.ugc.sensitive.IInputContentCheckingPolicy;
import com.alibaba.middleware.policy.ugc.sensitive.NoSensitiveContentCheckingPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入过滤组件，支持多策略
 * @author Created by lemon.nzg@alibaba-inc.com on 2018/8/14.
 */
@Component
public class InputContentCheckComponent implements IInputContentCheckingPolicy {

    @Value("${cmp.ugc.local.part.match.keyword.files:''}")
    private String partFiles;

    @Value("${cmp.ugc.local.complete.match.keyword.files:''}")
    private String completeFiles;

    private List<IInputContentCheckingPolicy> policies = new ArrayList<>();

    @Override
    public boolean hasSensitive(String content) {
        if(policies.size() == 0){
            policies.add(new NoSensitiveContentCheckingPolicy(
                    new PartSensitiveWordsProcessor(partFiles), new CompleteMatchSensitiveWordsProcessor(completeFiles)
            ));
        }
        for (IInputContentCheckingPolicy policy : policies) {
            if (policy.hasSensitive(content)) {
                return true;
            }
        }
        return false;
    }



}
