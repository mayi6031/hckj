package com.hckj.product.microservice.service.blockQueue;

import com.alibaba.fastjson.JSON;
import com.hckj.common.cache.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 阻塞队列业务实现类
 *
 * @author ：yuhui
 */
@Service
public class BlockQueueBusinessProcess {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 阻塞队列异步解耦实现
     *
     * @param queueKey
     * @param value
     */
    public void processBusiness(String queueKey, String value) {
        if (queueKey.equals(BlockQueueEnum.BLOCK_QUEUE_TEST.getCode())) {
            dealBlockQueueTest(queueKey, value);
        } else if (queueKey.equals(BlockQueueEnum.BLOCK_QUEUE_TEST2.getCode())) {
            dealBlockQueueTest2(queueKey, value);
        }
    }

    private void dealBlockQueueTest(String queueKey, String value) {
        logger.info(Thread.currentThread().getName() + ",test," + JSON.toJSONString(value));
    }

    private void dealBlockQueueTest2(String queueKey, String value) {
        logger.info(Thread.currentThread().getName() + ",test2," + JSON.toJSONString(value));
    }

}
