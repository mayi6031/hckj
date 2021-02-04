package com.hckj.product.microservice.config;

import com.hckj.common.mq.rabbitmq.RabbitMqUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${mq.defaultExchange}")
    private String defaultExchange;
    @Value("${mq.routeKey}")
    private String routeKey;
    @Value("${mq.queue}")
    private String queue;

    public String getDefaultExchange() {
        return defaultExchange;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public String getQueue() {
        return queue;
    }

    @Bean
    public Queue queue() {
        boolean durable = true;
        boolean exclusive = false;
        boolean autoDelete = false;
        return new Queue(queue, durable, exclusive, autoDelete);
    }

    @Bean
    public DirectExchange defaultExchange() {
        boolean durable = true;
        boolean autoDelete = false;
        return new DirectExchange(defaultExchange, durable, autoDelete);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(defaultExchange())
                .with(routeKey);
    }

    /**
     * 生产者设置内容类型
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    /**
     * 消费者设置内容类型
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    /**
     * rabbitmq工具类
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitMqUtil rabbitmq(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = rabbitTemplate(connectionFactory);
        return new RabbitMqUtil(rabbitTemplate, getDefaultExchange(), getRouteKey());
    }
}
