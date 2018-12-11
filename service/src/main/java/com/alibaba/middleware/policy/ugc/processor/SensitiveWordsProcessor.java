package com.alibaba.middleware.policy.ugc.processor;

/**
 * 敏感词检索处理器
 */
public interface SensitiveWordsProcessor {

    /**
     * 查找是否包含违禁关键字，true为包含
     *
     * @param content 被检查的内容信息
     * @return
     */
    boolean existedSensitiveWords(String content);
}
