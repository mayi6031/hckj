package com.hckj.common.mq.rocketmq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.hckj.common.mq.rocketmq.support.RocketmqTopicTagEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Rocketmq消息发送类
 *
 * @author yuhui
 */
public class RocketmqMessageSender {
    private static final Logger logger = LoggerFactory.getLogger(RocketmqMessageSender.class);

    @Resource
    private DefaultMQProducer defaultMQProducer;

    public SendResult send(RocketmqTopicTagEnum rocketmqTopicTagEnum, String msgInfo) {
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(rocketmqTopicTagEnum.getTopic(), rocketmqTopicTagEnum.getTag(), "", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

}
