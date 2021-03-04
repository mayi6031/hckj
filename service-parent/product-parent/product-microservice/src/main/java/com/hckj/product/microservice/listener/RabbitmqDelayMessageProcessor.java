package com.hckj.product.microservice.listener;

import com.hckj.common.mq.rabbitmq.delay.constant.DelayQueuePrefix;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;


/**
 * 2019/5/13
 * created by 余辉
 */
@Component
public class RabbitmqDelayMessageProcessor {
    private static final Logger log = LoggerFactory.getLogger(RabbitmqDelayMessageProcessor.class);

    @RabbitListener(queues = DelayQueuePrefix.DELAYED_QUEUE_NAME)
    public void receiveDel(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("延时队列收到消息：{}，当前时间：{}", msg, new Date());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
