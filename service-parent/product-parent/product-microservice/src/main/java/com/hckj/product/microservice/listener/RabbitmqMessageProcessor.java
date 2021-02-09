package com.hckj.product.microservice.listener;

import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.domain.mq.BusiType;
import com.hckj.common.domain.mq.BusiTypeHandler;
import com.hckj.common.mq.rabbitmq.RabbitmqMessageListener;
import com.hckj.common.mq.rabbitmq.RabbitmqMessageSender;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 2019/5/13
 * created by 余辉
 */
@Component
public class RabbitmqMessageProcessor implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqMessageProcessor.class);

    @Autowired
    private RabbitmqMessageSender rabbitmqMessageSender;
    @Autowired
    private RedisUtil redisUtil;

    @RabbitmqMessageListener(value = BusiTypeHandler.ORDER_CREATE_BUSI_TASK, concurrentConsumers = "1", maxConcurrentConsumers = "1", prefetchCount = 1)
    public void processMessage(String key, String value) {
//        log.info("监听到消息{},key{},时间{}", value, key, new Date());
        if (StringUtils.isNotBlank(key)) {
            try {

            } catch (Exception e) {
                log.info("业务处理失败{}", e.getMessage());
            }
        }
    }

    @RabbitmqMessageListener(value = BusiTypeHandler.ORDER_CREATE_BUSI_TASK, concurrentConsumers = "1", maxConcurrentConsumers = "1", prefetchCount = 1)
    public void processMessage2(String key, String value) {
//        log.info("监听到消息{},key{},时间{}", value, key, new Date());
        if (StringUtils.isNotBlank(key)) {
            try {

            } catch (Exception e) {
                log.info("业务处理失败2{}", e.getMessage());
            }
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        for (int k = 1; k <= 1000; k++) {
            rabbitmqMessageSender.send(BusiType.ORDER_CREATE_BUSI, "order_" + k, "orderData_" + k);
        }
    }
}
