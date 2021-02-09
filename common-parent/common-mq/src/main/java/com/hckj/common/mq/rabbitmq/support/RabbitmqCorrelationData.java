package com.hckj.common.mq.rabbitmq.support;

import com.hckj.common.domain.mq.BusiType;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class RabbitmqCorrelationData extends CorrelationData {
    private BusiType busiType;

    public RabbitmqCorrelationData(String id, BusiType busiType) {
        super(id);
        this.busiType = busiType;
    }

    public BusiType getBusiType() {
        return busiType;
    }
}
