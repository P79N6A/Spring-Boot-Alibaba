package com.alibaba.middleware.policy.chance;

import com.alibaba.middleware.exp.BizException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by lemon on 2018/8/15.
 */
public class DrawPolicy {
    /**
     * 根据Math.random()产生一个double型的随机数，判断每个奖品出现的概率
     * 这里只实现权重的概率，如果需要加入数量和价值等概率计算，可以继承实现
     * @param prizes
     * @return random：奖品列表prizes中的序列（prizes中的第random个就是抽中的奖品）
     */
    public static Prize getPrizeIndex(List<Prize> prizes) {
        int random = -1;
        try {
            //计算总权重
            double sumWeight = 0;
            for (Prize p : prizes) {
                BigDecimal v1 = new BigDecimal(Double.toString(p.getWeight()));
                BigDecimal v2 = new BigDecimal(Double.toString(sumWeight));
                sumWeight = v1.add(v2).doubleValue();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < prizes.size(); i++) {
                d2 += Double.parseDouble(String.valueOf(prizes.get(i).getWeight())) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(prizes.get(i - 1).getWeight())) / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber < d2) {
                    random = i;
                    break;
                }
            }
        } catch (Exception e) {
            throw new BizException("CREAT_PROBABLY_ERROR");
        }
        return prizes.get(random);
    }
}
