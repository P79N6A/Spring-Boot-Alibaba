package com.alibaba.middleware.policy.random;

import com.alibaba.middleware.utils.LogsClick;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ellison on 2017/10/19.
 */
public class RandomFileLineConfigValuePolicy implements IRandomConfigValuePolicy {

    private List<String> values = new ArrayList<>();

    private Random random = new Random();

    public RandomFileLineConfigValuePolicy(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        Resource resource = new ClassPathResource(filePath);

        try (BufferedReader bw = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = bw.readLine()) != null) {
                values.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            LogsClick.logException("FileNotFoundException ",e);
            e.printStackTrace();
        } catch (IOException e) {
            LogsClick.logException("IOException ",e);
            e.printStackTrace();
        }
    }

    @Override
    public String get() {
        return values.get(random.nextInt(values.size()));
    }
}
