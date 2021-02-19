package com.hckj.product.microservice.controller;

import com.alibaba.fastjson.JSON;
import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.mongo.domain.model.user.User;
import com.hckj.common.mq.rabbitmq.RabbitmqMessageSender;
import com.hckj.common.web.DataResponse;
import com.hckj.product.microservice.service.blockQueue.BlockQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
    private MongoTemplate mongoTemplate;

    @Autowired
    private RabbitmqMessageSender rabbitmqMessageSender;

    @Autowired
    private BlockQueueService blockQueueService;

    @PostMapping("/test")
    public DataResponse<String> test(@RequestBody String name) {
        // 测试Redis
        redisUtil.set(name, "test_" + name);
        logger.info("test_redis:" + redisUtil.get(name));
        // 测试Mongo
        Query query = new Query();
        List<User> tmpList = mongoTemplate.find(query, User.class);
        logger.info("test_mongo:" + JSON.toJSONString(tmpList));
        return DataResponse.ok(name);
    }

    @PostMapping("/delayMsg")
    public DataResponse<String> delayMsg(String msg, Integer delayTime) {
        logger.info("发送消息：{}，时间：{},延迟时间:{}", msg, new Date(), delayTime);
        rabbitmqMessageSender.sendDelayMsg(msg, delayTime);
        return DataResponse.ok("ok");
    }

    @PostMapping("/sendMsg")
    public DataResponse<String> sendMsg(String key, String value) {
        logger.info("sendMsg,key：{}，value：{}", key, value);
        for (int k = 0; k < 100; k++) {
            blockQueueService.pushToQueue(key + "-" + k, value + "-" + k);
        }
        return DataResponse.ok("ok");
    }
}
