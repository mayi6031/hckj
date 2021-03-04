package com.hckj.product.microservice.controller;

import com.alibaba.fastjson.JSON;
import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.feign.product.ProductInnovateFeign;
import com.hckj.common.web.DataResponse;
import com.hckj.product.microservice.service.impl.ProductInnovateService;
import com.hckj.product.microservice.service.TccProductInnovateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品信息Controller
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/5 15:26
 */
@Api(value = "创新产品相关", tags = "ProductInnovate")
@RestController
public class ProductInnovateController implements ProductInnovateFeign {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductInnovateService productInnovateService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TccProductInnovateService tccProductInnovateService;

    @Override
    @ApiOperation(value = "查询产品", notes = "根据产品id获取创新产品信息", response = DataResponse.class)
    public DataResponse<ProductInnovateModel> getProductInnovateInfo(@RequestBody Integer id) {
        logger.info("getProductInfo，参数：" + id);
        try {
            if (id == null) {
                return DataResponse.no("参数不能为空");
            }
            ProductInnovateModel productInnovateModel = null;
            String key = "productInfoTest:" + id;
            String val = redisUtil.get(key);
            if (val == null) {
                productInnovateModel = productInnovateService.selectById(id);
                if (productInnovateModel != null) {
                    String tmpVal = JSON.toJSONString(productInnovateModel);
                    redisUtil.set(key, tmpVal);
                }
            } else {
                productInnovateModel = JSON.parseObject(val, ProductInnovateModel.class);
            }
            return DataResponse.ok(productInnovateModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @Override
    @ApiOperation(value = "修改产品", notes = "根据产品id修改创新产品信息", response = DataResponse.class)
    public DataResponse<String> updateProductInnovateInfo(@RequestBody Integer id) {
        logger.info("updateProductInnovateInfo，参数：" + id);
        try {
            if (id == null) {
                return DataResponse.no("参数不能为空");
            }
            ProductInnovateModel productInnovateModel = new ProductInnovateModel();
            productInnovateModel.setId(id);
            int result = tccProductInnovateService.updateProductInnovate(null, productInnovateModel);
            logger.info("updateProductInnovateInfo，影响行数：" + id + ",result:" + result);
            return DataResponse.ok("success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @Override
    @ApiOperation(value = "修改产品测试", notes = "根据产品id修改创新产品信息", response = DataResponse.class)
    public DataResponse<String> updateProductInnovateInfoTest(@RequestBody Integer id) {
        logger.info("updateProductInnovateInfoTest，参数：" + id);
        try {
            if (id == null) {
                return DataResponse.no("参数不能为空");
            }
            ProductInnovateModel productInnovateModel = new ProductInnovateModel();
            productInnovateModel.setId(id);
            productInnovateModel.setCode(System.currentTimeMillis() + "tmp");
            int result = productInnovateService.updateProductInnovate(productInnovateModel);
            logger.info("updateProductInnovateInfoTest，影响行数：" + id + ",result:" + result);
            return DataResponse.ok("success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @Override
    @ApiOperation(value = "测试", notes = "测试信息", response = DataResponse.class)
    public DataResponse<?> sayHello(@RequestBody Integer id) {
        logger.info("sayHello，参数：" + id);
        return DataResponse.ok("sayHello," + id);
    }
}
