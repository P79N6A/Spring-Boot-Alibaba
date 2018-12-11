package com.alibaba.middleware.policy.ugc.processor;


/**
 * 局部敏感词校验，非全词匹配（包含关键词,局部匹配实现机制）
 */

public class PartSensitiveWordsProcessor extends AbstractLocalSensitiveWordsProcessor {


    public PartSensitiveWordsProcessor(String partFiles) {
        super(partFiles.split(","));
    }

    @Override
    public boolean existedSensitiveWords(String content) {
        return checkContentWithoutCompleteMatch(content);
    }

}
