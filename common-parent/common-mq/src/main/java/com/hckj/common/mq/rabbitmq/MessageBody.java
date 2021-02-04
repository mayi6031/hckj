package com.hckj.common.mq.rabbitmq;

import java.io.Serializable;

public class MessageBody<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer type;// 消息业务类型

    private long createTime;// 消息创建时间

    private T result; // 数据信息

    public MessageBody() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
