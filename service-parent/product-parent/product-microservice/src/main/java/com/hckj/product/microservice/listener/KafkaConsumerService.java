package com.hckj.product.microservice.listener;

import com.alibaba.fastjson.JSON;
import com.hckj.common.domain.kafka.TopicType;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.mq.kafka.KafkaMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    protected final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaMessageListener(value = TopicType.TOPIC_KAFKA)
    public void consume(String key, ProductInnovateModel value) {
        logger.info("--receive-{},{}", key, JSON.toJSONString(value));
    }

    @KafkaMessageListener(value = TopicType.TOPIC_KAFKA_TEST)
    public void consume2(String key, Long value) {
        logger.info("--receiveTest-{},{}", key, JSON.toJSONString(value));
    }

}
