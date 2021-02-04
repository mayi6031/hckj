package com.hckj.product.microservice.listener;

import com.alibaba.fastjson.JSON;
import com.hckj.common.mq.rabbitmq.MessageBody;
import com.hckj.common.mq.rabbitmq.MessageTypeEnum;
import com.hckj.common.mq.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


/**
 * 2019/5/13
 * created by 余辉
 */
@Component
public class MessageListener implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private RabbitMqUtil rabbitMqUtil;

    @RabbitListener(queues = "${mq.queue}")
    public void receive(MessageBody<?> messageBody, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("receive--" + JSON.toJSONString(messageBody));
        rabbitMqUtil.ackMessage(channel, tag, false);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (int k = 0; k < 1; k++) {
            rabbitMqUtil.sendMessage(MessageTypeEnum.PRODUCT_CREATE, "abc");
            log.info("produce--" + k);
        }
    }
}
