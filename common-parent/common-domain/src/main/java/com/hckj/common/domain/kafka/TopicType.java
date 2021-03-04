package com.hckj.common.domain.kafka;

import com.hckj.common.domain.product.model.ProductInnovateModel;

/**
 * 主体类型
 */
public enum TopicType {

    TOPIC_KAFKA("topic_kafka", ProductInnovateModel.class),
    TOPIC_KAFKA_TEST("topic_kafka_test", Long.class)//
    ;

    TopicType(String topicName, Class<?> busiObjectClass) {
        this.topicName = topicName;
        this.busiObjectClass = busiObjectClass;
    }

    private String topicName;
    private Class<?> busiObjectClass;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Class<?> getBusiObjectClass() {
        return busiObjectClass;
    }

    public void setBusiObjectClass(Class<?> busiObjectClass) {
        this.busiObjectClass = busiObjectClass;
    }}
