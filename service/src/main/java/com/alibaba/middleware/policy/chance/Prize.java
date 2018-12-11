package com.alibaba.middleware.policy.chance;

import java.io.Serializable;

/**
 * @author Created by lemon on 2018/8/15.
 */
public class Prize implements Serializable {

    private static final long serialVersionUID = 1L;

    // 实物
    public static final int ENTITY = 1;
    // 虚拟
    public static final int VIRTUAL = 2;
    // 优惠券
    public static final int COUPON = 3;
    // 默认
    public static final int UNKNOWN = 0;

    /**
     * 奖品id
     */
    private Integer id;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品（剩余）数量 默认无限
     */
    private Integer amount = -1;

    /**
     * 奖品权重 如果小数点
     */
    private Double weight;

    /**
     * 价值
     */
    private Integer value;

    /**
     * 类型 自定义标示
     */
    private Integer type;

    public Prize() {}

    public Prize(Integer id, String prizeName, Double weight) {
        this.id = id;
        this.prizeName = prizeName;
        this.weight = weight;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
