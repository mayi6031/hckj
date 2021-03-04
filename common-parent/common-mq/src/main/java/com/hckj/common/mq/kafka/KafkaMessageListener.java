package com.hckj.common.mq.kafka;

import com.hckj.common.domain.kafka.TopicType;
import com.hckj.common.domain.mq.BusiType;

import java.lang.annotation.*;

/**
 * 方法必须包含两个参数 <br/>
 * 第一个为busiId: {@link String} <br/>
 * 第二个为busiObject: 对应{@link BusiType}构造函数传入的{@code Class<?>}
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KafkaMessageListener {
    TopicType value();

    String[] topics() default {};
}