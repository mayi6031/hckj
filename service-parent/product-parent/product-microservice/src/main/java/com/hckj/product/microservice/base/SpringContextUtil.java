package com.hckj.product.microservice.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获得Spring应用上下文
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入,请先定义再使用！");
        }
        return applicationContext;
    }

    /**
     * 根据beanId返回Spring中的实例
     *
     * @param beanId
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(String beanId) throws BeansException {
        return (T) applicationContext.getBean(beanId);
    }

    /**
     * 根据类返回Spring中的实例
     *
     * @param clz
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T) applicationContext.getBean(clz);
    }

}