package com.hckj.product;

import com.alibaba.fastjson.JSON;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.mongo.domain.model.user.User;
import com.hckj.common.mongo.utils.MongoOperateBase;
import com.hckj.common.mongo.utils.MongoOperateMerge;
import com.hckj.common.mongo.utils.MultiOprHelp;
import com.hckj.product.microservice.ProductServiceMain;
import com.hckj.product.microservice.base.SpringContextUtil;
import com.hckj.product.microservice.service.ProductInnovateService;
import com.hckj.product.microservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 创新产品测试类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/2 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceMain.class)
public class MongoTest {
    private static final Logger logger = LoggerFactory.getLogger(MongoTest.class);

    @Test
    public void getData() {
        try {
            UserService userService = SpringContextUtil.getBean(UserService.class);

            MultiOprHelp multiOpr = MongoOperateBase.begin().in("name", Arrays.asList("test", "酒仙")).end();

            MultiOprHelp multiOpr2 = MongoOperateBase.begin().is("username", "zhangsan").is("name", "酒仙").or().end();

            MultiOprHelp multiOpr3 = MongoOperateBase.begin().like("name", "酒").is("name", "酒仙").and().end();

            Criteria criteria = MongoOperateMerge.empty().and(multiOpr).or(multiOpr2).or(multiOpr3).collect();

            List<User> result = userService.findByCondition(criteria);
            logger.info("res:" + JSON.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
