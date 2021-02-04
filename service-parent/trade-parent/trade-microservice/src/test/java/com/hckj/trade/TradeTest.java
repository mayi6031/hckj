package com.hckj.trade;

import com.alibaba.fastjson.JSON;
import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.trade.microservice.TradeServiceMain;
import com.hckj.trade.microservice.service.SysConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 交易测试类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/2 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeServiceMain.class)
public class TradeTest {
    private static final Logger logger = LoggerFactory.getLogger(TradeTest.class);

    @Autowired
    private SysConfigService sysConfigService;

    @Test
    public void getData() {
        SysConfigModel sysConfigModel = sysConfigService.selectById(1);
        logger.info(JSON.toJSONString(sysConfigModel));
    }
}
