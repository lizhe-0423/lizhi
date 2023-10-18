package com.lizhi.manage;
import com.lizhi.common.ErrorCode;
import com.lizhi.utils.ThrowUtil;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 专门提供Redis Limit 提供基础服务的
 * @author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
 */
@Service
public class RedisLimitManager {
    @Resource
    private RedissonClient redissonClient;

    /**
     * 限流操作 普通用户每秒钟2次
     * @param key 区分不同的限流器
     */
    public void doRateLimitByUser(String key){
        //声明一个限流器
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        //设置限流器的规则 每秒钟两次
        rateLimiter.trySetRate(RateType.OVERALL,2,1, RateIntervalUnit.SECONDS);
        //每当操作来之后 请求一个令牌
        boolean canOp = rateLimiter.tryAcquire(1);
        ThrowUtil.throwIf(!canOp, ErrorCode.TO_MANY_REQUEST);
    }
    /**
     * 限流操作 VIP用户每秒钟5次
     * @param key 区分不同的限流器
     */
    public void doRateLimitByVip(String key){
        //声明一个限流器
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        //设置限流器的规则 每秒钟两次
        rateLimiter.trySetRate(RateType.OVERALL,5,1, RateIntervalUnit.SECONDS);
        //每当操作来之后 请求一个令牌
        boolean canOp = rateLimiter.tryAcquire(1);
        ThrowUtil.throwIf(!canOp, ErrorCode.TO_MANY_REQUEST);
    }

}
