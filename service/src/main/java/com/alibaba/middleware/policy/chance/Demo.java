package com.alibaba.middleware.policy.chance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by lemon on 2018/8/15.
 */
public class Demo {
    public void test(){

        //从配置文件获取概率,也可以浮点数："0.6,0.3,0.1"
        String[] pros = "60,30,10".split(",");

        //初始化奖品
        List<Prize> prizes = new ArrayList<>();
        prizes.add(new Prize(1, "铜牌", Double.parseDouble(pros[0])));
        prizes.add(new Prize(2, "银牌", Double.parseDouble(pros[1])));
        prizes.add(new Prize(3, "金牌", Double.parseDouble(pros[2])));

        //抽奖品
        Prize prize = DrawPolicy.getPrizeIndex(prizes);
        System.out.println("抽到的奖品为："+prize.getPrizeName());
    }
}
