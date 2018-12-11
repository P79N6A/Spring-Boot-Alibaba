package com.alibaba.middleware.policy.ugc.processor;


import org.apache.commons.lang3.StringUtils;

/**
 * 精准匹配敏感词，会对语句进行简单分词（如根据空格、逗号、分号等进行分割），暂不支持中文分词，
 * @author lemon
 */
public class CompleteMatchSensitiveWordsProcessor extends AbstractLocalSensitiveWordsProcessor {


    public CompleteMatchSensitiveWordsProcessor(String keywordFilePaths) {
        super(keywordFilePaths.split(","));
    }

    @Override
    public boolean existedSensitiveWords(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        String splitRule = " |\\.|\\。|\\,|\\，|\\;|\\；|\\、|\\?|\\!|\\:|\\：";
        String[] words = content.split(splitRule);
        boolean checkRes;
        for (String word : words) {
            checkRes = checkContentWithCompleteMatch(word);
            if (checkRes) {
                return true;
            }
        }
        return false;
    }

}
