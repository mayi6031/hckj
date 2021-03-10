package com.hckj.product.microservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.domain.kafka.TopicType;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.mq.activemq.ActivemqMessageSender;
import com.hckj.common.mq.activemq.support.ActivemqDestinationEnum;
import com.hckj.common.mq.kafka.KafkaMessageSender;
import com.hckj.common.mq.rabbitmq.RabbitmqMessageSender;
import com.hckj.common.mq.rocketmq.RocketmqMessageSender;
import com.hckj.common.mq.rocketmq.support.RocketmqTopicTagEnum;
import com.hckj.common.web.DataResponse;
import com.hckj.product.microservice.service.blockQueue.BlockQueueEnum;
import com.hckj.product.microservice.service.blockQueue.BlockQueueProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试专用
 *
 * @author ：yuhui
 * @date ：Created in 2020/10/9 10:01
 */
@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitmqMessageSender rabbitmqMessageSender;

    @Autowired
    private BlockQueueProcessor blockQueueProcessor;

    @Autowired
    private KafkaMessageSender kafkaMessageSender;

    @Autowired
    private ActivemqMessageSender activemqSender;

    @Autowired
    private RocketmqMessageSender rocketmqMessageSender;

    @PostMapping("/test")
    public DataResponse<String> test(@RequestBody String name) {
        // 测试Redis
        redisUtil.set(name, "test_" + name);
        logger.info("test_redis:" + redisUtil.get(name));

        SendResult sendResult = rocketmqMessageSender.send(RocketmqTopicTagEnum.TOPIC_TAG_TEST, name);
        logger.info("sendResult:" + JSON.toJSONString(sendResult));
        return DataResponse.ok(name);
    }

    @PostMapping("/delayMsg")
    public DataResponse<String> delayMsg(String msg, Integer delayTime) {
        logger.info("发送消息：{}，时间：{},延迟时间:{}", msg, new Date(), delayTime);
        rabbitmqMessageSender.sendDelayMsg(msg, delayTime);

        new Thread(() -> {
            for (int k = 1; k <= 50; k++) {
                blockQueueProcessor.pushToQueue(BlockQueueEnum.BLOCK_QUEUE_TEST, "test-" + k);
            }
        }).start();
        new Thread(() -> {
            for (int k = 51; k <= 100; k++) {
                blockQueueProcessor.pushToQueue(BlockQueueEnum.BLOCK_QUEUE_TEST2, "test-" + k);
            }
        }).start();
        return DataResponse.ok("ok");
    }

    @PostMapping("/sendMsg")
    public DataResponse<String> sendMsg(String value, Integer delayTime) {
        logger.info("sendMsg,value：{}", value);
        try {
            ProductInnovateModel productInnovateModel = new ProductInnovateModel();
            productInnovateModel.setName(value);
            productInnovateModel.setProductTagId(1);
            kafkaMessageSender.send(TopicType.TOPIC_KAFKA, String.valueOf(System.currentTimeMillis()), productInnovateModel);
            kafkaMessageSender.producerSend(TopicType.TOPIC_KAFKA_TEST, String.valueOf(System.currentTimeMillis()), 22l);
        } catch (Exception e) {
            logger.error("kafka send message error,please see db log", e);
        }

        ProductInnovateModel productInnovateModel = new ProductInnovateModel();
        productInnovateModel.setName(value);
        productInnovateModel.setProductTagId(1);
        activemqSender.send(ActivemqDestinationEnum.ACTIVEMQ_TEST, "test");
        activemqSender.send(ActivemqDestinationEnum.ACTIVEMQ_TEST2, JSON.toJSONString(productInnovateModel), delayTime);
        return DataResponse.ok("ok");
    }

}
