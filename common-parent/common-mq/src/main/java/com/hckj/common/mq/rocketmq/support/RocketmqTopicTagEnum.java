package com.hckj.common.mq.rocketmq.support;

public enum RocketmqTopicTagEnum {


    TOPIC_TAG_TEST("topic_test", "tag1"),
    TOPIC_TAG_TEST2("topic_test2", "tag1"),
    ;

    private String topic;
    private String tag;

    RocketmqTopicTagEnum(String topic, String tag) {
        this.topic = topic;
        this.tag = tag;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
