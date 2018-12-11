package com.alibaba.middleware;

import com.alibaba.middleware.annotation.ReturnHandler;
import com.alibaba.middleware.container.redis.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by lemon on 2018/8/13.
 */
@RestController
public class AppController {

    /**
     * 健康检查，系统部署需要
     * 请不要删除！！
     */
    @GetMapping("/")
    @ReturnHandler
    public Object checkPreload() {
        Map map  = new HashMap<>();
        map.put("check_preload","success");
        return map;
    }

}
