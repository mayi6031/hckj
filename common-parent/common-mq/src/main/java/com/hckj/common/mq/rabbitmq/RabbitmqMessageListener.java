package com.hckj.common.mq.rabbitmq;

import com.hckj.common.domain.mq.BusiType;
import com.hckj.common.domain.mq.BusiTypeHandler;

import java.lang.annotation.*;

/**
 * 方法必须包含两个参数 <br/>
 * 第一个为busiId: {@link String} <br/>
 * 第二个为busiObject: 对应{@link BusiType}构造函数传入的{@code Class<?>}
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RabbitmqMessageListener {
    BusiTypeHandler value();

    /**
     * 同时监听数量
     */
    String concurrentConsumers();

    /**
     * 最大同时监听数量
     */
    String maxConcurrentConsumers();

    /**
     * 设置预取数量
     */
    int prefetchCount() default -1;
}