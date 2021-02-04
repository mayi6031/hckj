package com.hckj.product.microservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.exception.business.BusinessException;
import com.hckj.product.microservice.dao.ProductInnovateDao;
import com.hckj.product.microservice.service.TccProductInnovateService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tcc实现
 *
 * @author ：yuhui
 * @date ：Created in 2021/1/28 14:00
 */
@Service
public class TccProductInnovateServiceImpl implements TccProductInnovateService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String PRODUCT_PREFIX = "productInnovate-";

    @Autowired
    private ProductInnovateDao productInnovateDao;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int updateProductInnovate(BusinessActionContext businessActionContext, ProductInnovateModel productInnovateModel) throws Exception {
        ProductInnovateModel innovateModel = productInnovateDao.selectByPrimaryKey(productInnovateModel.getId());
        if (innovateModel == null) {
            logger.error("该产品信息不存在：" + productInnovateModel.getId());
            throw new BusinessException("该产品信息不存在:" + productInnovateModel.getId());
        }
        String key = PRODUCT_PREFIX + productInnovateModel.getId();
        redisUtil.setnx(key, innovateModel.getCode(), 10);
        productInnovateModel.setCode(System.currentTimeMillis() / 1000 + "");
        int res = productInnovateDao.updateByPrimaryKeySelective(productInnovateModel);
        logger.info("一阶段准备影响行数：" + res);
        return res;
    }

    @Override
    public boolean commitUpdateProductInnovateTcc(BusinessActionContext context) throws Exception {
        ProductInnovateModel productInnovateModel = JSON.parseObject(JSON.toJSONString(context.getActionContext("productInnovateModel")), ProductInnovateModel.class);
        if (productInnovateModel == null) {
            logger.error("二阶段提交失败");
            return false;
        }
        String key = PRODUCT_PREFIX + productInnovateModel.getId();
        redisUtil.del(key);
        logger.info("二阶段提交");
        return true;
    }

    @Override
    public boolean rollbackUpdateProductInnovateTcc(BusinessActionContext context) throws Exception {
        ProductInnovateModel productInnovateModel = JSON.parseObject(JSON.toJSONString(context.getActionContext("productInnovateModel")), ProductInnovateModel.class);
        if (productInnovateModel == null) {
            logger.error("二阶段回滚失败");
            return false;
        }
        String key = PRODUCT_PREFIX + productInnovateModel.getId();
        String oldCode = redisUtil.get(key);
        if(oldCode == null){
            oldCode = "default";
        }
        productInnovateModel.setCode(oldCode);
        int res = productInnovateDao.updateByPrimaryKeySelective(productInnovateModel);
        logger.info("二阶段回滚结果：" + res);
        return true;
    }
}
