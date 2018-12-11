package com.alibaba.middleware.container.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * redis封装
 * 增加并发锁和pv锁
 * @author: Created by lemon.nzg@alibaba-inc.com on 2018/6/20 11:37
 */
@Component
public class Redis {

    /**
     * 功能描述:redis有效过期时间 12小时
     */
    public static final long EXP12H = 43200;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Set {@code value} for {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
     */
    public <T> void set(String key, T value) {
        if (value != null) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * Set {@code value} for {@code key}.
     *
     * @param key            must not be {@literal null}.
     * @param value
     * @param timeoutSeconds seconds not be {@long null}
     * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
     */
    public <T> void set(String key, T value, long timeoutSeconds) {
        if (value != null) {
            redisTemplate.opsForValue().set(key, value, timeoutSeconds, TimeUnit.SECONDS);
        }
    }
    public <T> void set(String key, T value, long timeoutSeconds,TimeUnit unit) {
        if (value != null) {
            redisTemplate.opsForValue().set(key, value, timeoutSeconds, unit);
        }
    }

    /**
     * Get the value of {@code key}.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/get">Redis Documentation: GET</a>
     */
    public <T> T get(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * Get the value of {@code key}.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/get">Redis Documentation: GET</a>
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Get multiple {@code keys}. Values are returned in the order of the requested keys.
     *
     * @param keys must not be {@literal null}.
     * @see <a href="http://redis.io/commands/mget">Redis Documentation: MGET</a>
     */
    public <T> List<T> mget(List<String> keys) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.multiGet(keys);
    }

    /**
     * Get the value of {@code key}.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/hasKey">Redis Documentation: HASKEY</a>
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * Increment an integer value stored as string value under {@code key} by {@code delta}.
     *
     * @param key must not be {@literal null}.
     *            if has(key) no exit ,incr default key for 1
     * @see <a href="http://redis.io/commands/incr">Redis Documentation: INCR</a>
     */
    public long incr(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.incrementAndGet();
        return increment;
    }

    /**
     * Increment an integer value stored as string value under {@code key} by {@code delta}.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/incr">Redis Documentation: INCR</a>
     */
    public Long decr(String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long decrement = entityIdCounter.decrementAndGet();
        return decrement;
    }


    /**
     * Get the length of the value stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @see <a href="http://redis.io/commands/strlen">Redis Documentation: STRLEN</a>
     */
    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     *
     * 处理并发 支持百万级别
     * 用于抢占资源，数量有限，用于秒杀业务
     * @param pv 初始化并发时候最多抢到的数量
     * @return 是否抢到资源
     *
     */
    public boolean pv(String key,Integer pv) {
        if (get(key) == null) {
            set(key, pv);
        }

        if(get(key)!=null && (Integer)get(key) > 0){
            if (decr(key) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置pv量
     */
    public void setPv(String key,Integer pv){
        set(key, pv);
    }

    /**
     * 获取锁 使用集群实现高可用
     * 用于业务需要排队等待 比如在原始数据不允许同时修改数据，不允许并发执行
     * @param lockID 每2秒不断的获取锁资源
     * @return
     */
    public boolean lock(String lockID){

        while(true){
            if (decr(lockID) == -1){
                System.out.println(Thread.currentThread().getName() + " get lock....");
                return true;
            }
            System.out.println(Thread.currentThread().getName() + " is trying lock....");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * 超时获取锁 使用集群实现高可用
     * 用于业务需要排队等待 比如在原始数据不允许同时修改数据，不允许并发执行
     * @param lockID
     * @param timeMillis MILLISECONDS
     * @return
     */
    public boolean lock(String lockID,long timeMillis){
        long current = System.currentTimeMillis();
        long future = current + timeMillis;
        long timeStep = 500;
        CountDownLatch latch = new CountDownLatch(1);
        while(future > current){
            if (decr(lockID) == -1){
                System.out.println(Thread.currentThread().getName() + " get lock....");
                set(lockID,-1,timeMillis,TimeUnit.MILLISECONDS);
                return true;
            }
            System.out.println(Thread.currentThread().getName() + " is trying lock....");
            try {
                latch.await(timeStep, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = current + timeStep;
        }
        return false;
    }


    /**
     * finally 解锁
     */
    public void unlock(String lockId){
        delete(lockId);
        System.out.println(Thread.currentThread().getName() + " release lock....");
    }





}
