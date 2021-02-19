package com.hckj.product.microservice.service.blockQueue;

import com.alibaba.fastjson.JSON;
import com.hckj.common.cache.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列(基于redis实现)
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/19 11:16
 */
@Service
public class BlockQueueService implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 处理异步请求的阻塞队列
    private LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

    @Autowired
    private RedisUtil redisUtil;

    private class BlockQueueServiceThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    String key = blockingQueue.take();
                    List<String> list = redisUtil.brpop(key);
                    logger.info(Thread.currentThread().getName() + "," + JSON.toJSONString(list));
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }


    // 把请求放入阻塞队列
    public void pushToQueue(String key, String value) {
        blockingQueue.add(key);
        redisUtil.lpush(key, value);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new BlockQueueServiceThread().start();
    }
}
