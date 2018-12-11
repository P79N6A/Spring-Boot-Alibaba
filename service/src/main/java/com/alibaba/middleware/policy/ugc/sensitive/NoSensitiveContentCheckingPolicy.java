package com.alibaba.middleware.policy.ugc.sensitive;


import com.alibaba.middleware.policy.ugc.processor.SensitiveWordsProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ellison on 2017/10/19.
 */
public class NoSensitiveContentCheckingPolicy implements IInputContentCheckingPolicy {

    private List<SensitiveWordsProcessor> sensitiveNickProcessors = new ArrayList<SensitiveWordsProcessor>();

    public NoSensitiveContentCheckingPolicy(SensitiveWordsProcessor... sensitiveWordsProcessors) {
        for (SensitiveWordsProcessor processor : sensitiveWordsProcessors) {
            sensitiveNickProcessors.add(processor);
        }
    }


    @Override
    public boolean hasSensitive(String content) {
        for (SensitiveWordsProcessor processor : sensitiveNickProcessors) {
            if (processor.existedSensitiveWords(content)) {
                return true;
            }
        }
        return false;
    }
}
