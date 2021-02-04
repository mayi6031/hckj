package com.hckj.order;

import com.alibaba.fastjson.JSON;
import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.order.microservice.OrderServiceMain;
import com.hckj.order.microservice.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 订单测试类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/2 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceMain.class)
public class OrderTest {
    private static final Logger logger = LoggerFactory.getLogger(OrderTest.class);

    @Resource
    private OrderService orderService;

    @Test
    public void getData() {
        SysConfigModel sysConfigModel = orderService.getSysConfigInfo(1);
        logger.info(JSON.toJSONString(sysConfigModel));
    }
}
