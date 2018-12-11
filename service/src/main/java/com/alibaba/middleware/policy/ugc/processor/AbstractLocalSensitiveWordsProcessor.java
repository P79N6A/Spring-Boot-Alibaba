package com.alibaba.middleware.policy.ugc.processor;


import com.alibaba.middleware.exp.BizException;
import com.alibaba.middleware.exp.CheckedException;
import com.alibaba.middleware.utils.LogsClick;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 实现本地敏感词过滤方法，通过dfa方法进行比较，可支持全词匹配和部分匹配功能
 * @author lemon
 */
public abstract class AbstractLocalSensitiveWordsProcessor implements SensitiveWordsProcessor {
    /**
     * 关键词检索索引集，用于dfa算法
     * 如fuck,funny两个关键词会拆分成如下结构：
     * f ->
     *      u ->
     *           c ->
     *                k -> boom
     *           n ->
     *                n ->
     *                     y -> boom
     *
     */
    private Map keywordIndexMap = new HashMap();
    /**
     * 关键词集合，用于分词后的全字符匹配校验
     */
    private Set<String> keywordSet = new HashSet<String>();


    public AbstractLocalSensitiveWordsProcessor(String[] keywordFilePaths) {
        initKeywordMap(keywordFilePaths);
    }

    /**
     * 查找是否包含违禁关键字，true为包含
     *
     * @param content 被检查的内容信息
     * @return
     */
    @Override
    public abstract boolean existedSensitiveWords(String content);

    /**
     * 非全词匹配校验（局部匹配）
     * 如：有敏感词abc，校验的内容为abcdef，则校验的内容包含敏感词
     *
     * @param content
     * @return
     */
    protected boolean checkContentWithoutCompleteMatch(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        content = content.trim().toLowerCase();
        int len = content.length();
        for (int i = 0; i < len; i++) {
            int start = i;
            Map nextMap = keywordIndexMap;
            Map curCharMap = null;
            for (int j = i; j < len; j++) {
                String curCharStr = String.valueOf(content.charAt(j));
                curCharMap = (Map) nextMap.get(curCharStr);
                if (curCharMap == null) {
                    break;
                } else {
                    if (curCharMap.containsKey("boom")) {
                        LogsClick.logException("input content has sensitive word, word=%s;content=%s;", content.substring(start, j + 1), content);
                        return true;
                    }
                }
                nextMap = curCharMap;
            }
        }
        return false;
    }

    /**
     * 全词校验（全部匹配）
     * 如：有敏感词abc，校验的内容为abcdef，则校验的内容不包含敏感词，只有输入校验的内容为abc才认为命中
     *
     * @param content
     * @return
     */
    protected boolean checkContentWithCompleteMatch(String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        return keywordSet.contains(content.trim().toLowerCase());
    }

    private void initKeywordMap(String[] filePaths) {
        for (String filePath : filePaths) {
            if (StringUtils.isEmpty(filePath)) {
                continue;
            }
            Resource resource = new ClassPathResource(filePath);
            try {
                processKeywordFile(resource.getInputStream());
            } catch (IOException e) {
                throw new BizException("sensitive file not found: "+e.toString());
            }
        }
    }



    /**
     * 处理关键词文件
     *
     * @param inputStream
     */
    private void processKeywordFile(InputStream inputStream) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            while (StringUtils.isNotEmpty(line)) {
                buildIndexMap(line.trim().toLowerCase());
                keywordSet.add(line.trim().toLowerCase());
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            throw new CheckedException("process sensitive file meet Exception");
        }
    }

    private void buildIndexMap(String line) {
        int len = line.length();
        Map currentMap = keywordIndexMap;
        for (int i = 0; i < len; i++) {
            String singleChar = String.valueOf(line.charAt(i));
            Object curCharMap = currentMap.get(singleChar);
            if (curCharMap == null) {
                curCharMap = new HashMap();
                currentMap.put(singleChar, curCharMap);
            }
            Map nowMap = (Map) curCharMap;
            //是否是最后一个字符
            if (i == (len - 1)) {
                //最后个字符上的映射map加上爆炸标记
                nowMap.put("boom", "true");
            }
            currentMap = nowMap;
        }
    }
}
