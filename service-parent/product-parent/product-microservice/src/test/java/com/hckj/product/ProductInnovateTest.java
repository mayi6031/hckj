package com.hckj.product;

import com.alibaba.fastjson.JSON;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.product.microservice.ProductServiceMain;
import com.hckj.product.microservice.service.impl.ProductInnovateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 创新产品测试类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/2 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceMain.class)
public class ProductInnovateTest {
    private static final Logger logger = LoggerFactory.getLogger(ProductInnovateTest.class);

    @Resource
    private ProductInnovateService productInnovateService;

    @Test
    public void getData() {
        ProductInnovateModel productInnovateModel = productInnovateService.selectById(1);
        logger.info(JSON.toJSONString(productInnovateModel));
    }
}
