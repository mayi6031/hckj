package com.hckj.common.mq.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import java.io.IOException;

/**
 * 消息统一处理模块
 * 2019/5/13
 * created by 余辉
 */
public class RabbitMqUtil {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqUtil.class);

    private AmqpTemplate amqpTemplate;
    private String defaultExchange;
    private String routeKey;

    public RabbitMqUtil(AmqpTemplate amqpTemplate, String defaultExchange, String routeKey) {
        this.amqpTemplate = amqpTemplate;
        this.defaultExchange = defaultExchange;
        this.routeKey = routeKey;
    }

    /**
     * 通过rabbit发送消息
     *
     * @param messageTypeEnum
     * @param messageObj
     */
    public <T> void sendMessage(MessageTypeEnum messageTypeEnum, T messageObj) {
        MessageBody<T> messageBody = new MessageBody<T>();
        messageBody.setType(messageTypeEnum.getCode());
        messageBody.setResult(messageObj);
        messageBody.setCreateTime(System.currentTimeMillis());
        try {
            amqpTemplate.convertAndSend(defaultExchange, routeKey, messageBody);
            log.info("发送rabbitmq内容：" + JSON.toJSONString(messageBody));
        } catch (Exception e) {
            log.error("rabbitmq发送消息出错了", e);
        }
    }

    /**
     * 确认删除
     *
     * @param channel
     * @param tag
     * @param multiple
     */
    public void ackMessage(Channel channel, long tag, boolean multiple) {
        try {
            channel.basicAck(tag, multiple);
        } catch (IOException e) {
            log.error("RabbitMQ，IO异常，异常原因为：{}" + e.getMessage(), e);
        }
    }

    /**
     * 消息回退
     *
     * @param channel
     * @param tag
     * @param multiple
     * @param request
     */
    public void rejectMessage(Channel channel, long tag, boolean multiple, boolean request) {
        try {
            channel.basicNack(tag, multiple, request);
        } catch (IOException e) {
            log.error("RabbitMQ，IO异常，异常原因为：{}" + e.getMessage(), e);
        }
    }
}
