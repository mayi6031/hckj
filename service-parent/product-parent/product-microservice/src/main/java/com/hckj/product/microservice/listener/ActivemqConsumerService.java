package com.hckj.product.microservice.listener;

import com.alibaba.fastjson.JSON;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.mq.activemq.support.ActivemqDestinationEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActivemqConsumerService {
    protected final Logger logger = LoggerFactory.getLogger(ActivemqConsumerService.class);

    @JmsListener(destination = ActivemqDestinationEnum.ACTIVEMQ_TEST)
    public void receive(String message) throws Exception {
        logger.info("从队列获取到数据：" + message);
    }

    @JmsListener(destination = ActivemqDestinationEnum.ACTIVEMQ_TEST2)
    public void receive2(String message) throws Exception {
        logger.info("从队列获取到数据2：" + message);
        ProductInnovateModel productInnovateModel = JSON.parseObject(message, ProductInnovateModel.class);
        logger.info("从队列获取到数据22：" + JSON.toJSONString(productInnovateModel));
    }

}
