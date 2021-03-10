package com.hckj.common.mq.rocketmq.support;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.hckj.common.mq.rocketmq.RocketmqMessageListener;
import com.hckj.common.mq.rocketmq.RocketmqMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.util.List;

/**
 * Rocketmq消息配置类
 *
 * @author yuhui
 */
public class RocketmqMessageConfig implements BeanPostProcessor, BeanFactoryAware {
    private static final Logger logger = LoggerFactory.getLogger(RocketmqMessageConfig.class);

    private DefaultListableBeanFactory beanFactory;

    @Value("${spring.rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${spring.rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${spring.rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${spring.rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${spring.rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;
    @Value("${spring.rocketmq.consumer.topics}")
    private String topics;
    @Value("${spring.rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize;
    @Value("${spring.rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    @Value("${spring.rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;

//    @Bean
//    public DefaultMQPushConsumer getRocketMQConsumer() {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
//        consumer.setNamesrvAddr(namesrvAddr);
//        consumer.setConsumeThreadMin(consumeThreadMin);
//        consumer.setConsumeThreadMax(consumeThreadMax);
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                if (CollectionUtils.isEmpty(list)) {
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//                MessageExt messageExt = list.get(0);
//                logger.info("接受到的消息为：" + new String(messageExt.getBody()));
//                int reConsume = messageExt.getReconsumeTimes();
//                // 消息已经重试了3次，如果不需要再次消费，则返回成功
//                if (reConsume == 3) {
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//                String content = new String(messageExt.getBody());
//                logger.info("topic:{},tag:{},message:{}", messageExt.getTopic(), messageExt.getTags(), content);
//                // 消息消费成功
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
//        try {
//            String[] topicTagsArr = topics.split(";");
//            for (String topicTags : topicTagsArr) {
//                String[] topicTag = topicTags.split("~");
//                consumer.subscribe(topicTag[0], topicTag[1]);
//            }
//            consumer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//        return consumer;
//    }

    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        if (this.maxMessageSize != null) {
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if (this.sendMsgTimeout != null) {
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if (this.retryTimesWhenSendFailed != null) {
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }

    @Bean
    public RocketmqMessageSender rocketmqMessageSender() {
        return new RocketmqMessageSender();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        ReflectionUtils.doWithMethods(targetClass, method -> {
            RocketmqMessageListener annotation = AnnotationUtils.getAnnotation(method, RocketmqMessageListener.class);
            if (annotation != null) {
                RocketmqTopicTagEnum handler = annotation.value();
                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
                consumer.setNamesrvAddr(namesrvAddr);
                consumer.setConsumeThreadMin(consumeThreadMin);
                consumer.setConsumeThreadMax(consumeThreadMax);
                consumer.registerMessageListener(new MessageListenerConcurrently() {
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                        if (CollectionUtils.isEmpty(list)) {
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                        MessageExt messageExt = list.get(0);
                        logger.info("接受到的消息为：" + new String(messageExt.getBody()));
                        int reConsume = messageExt.getReconsumeTimes();
                        // 消息已经重试了3次，如果不需要再次消费，则返回成功
                        if (reConsume == 3) {
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                        String content = new String(messageExt.getBody());
                        logger.info("topic:{},tag:{},message:{}", messageExt.getTopic(), messageExt.getTags(), content);
                        try {
                            method.invoke(bean, content);
                            logger.info("业务消息处理完成: {}", content);
                        } catch (Exception e) {
                            logger.error("业务消息处理异常: " + content + ", " + e.toString(), e);
                            throw new RuntimeException("业务消息处理异常:" + content + ", " + e.toString(), e);
                        }

                        // 消息消费成功
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                });
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
                consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
                try {
                    consumer.subscribe(handler.getTopic(), handler.getTag());
                    consumer.start();
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
            }
        }, ReflectionUtils.USER_DECLARED_METHODS);
        return bean;
    }
}