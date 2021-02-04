package com.hckj.trade.microservice.controller;

import com.alibaba.fastjson.JSON;
import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.common.exception.business.BusinessException;
import com.hckj.common.feign.product.ProductInnovateFeign;
import com.hckj.common.feign.trade.SysConfigFeign;
import com.hckj.common.web.DataResponse;
import com.hckj.trade.microservice.service.SysConfigService;
import com.hckj.trade.microservice.service.TccSysConfigService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置信息Controller
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/5 15:26
 */
@RestController
public class SysConfigController implements SysConfigFeign {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private ProductInnovateFeign productInnovateFeign;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TccSysConfigService tccSysConfigService;

    @Override
    public DataResponse<SysConfigModel> getSysConfigInfo(@RequestBody Integer id) {
        logger.info("getSysConfigInfo，参数：" + id);
        try {
            if (id == null) {
                return DataResponse.no("参数不能为空");
            }
            SysConfigModel sysConfigModel = getSysConfigModel(id);
            return DataResponse.ok(sysConfigModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @Override
    public DataResponse<SysConfigModel> getSysConfigInfoTest(@RequestBody Integer id) {
        logger.info("getSysConfigInfoTest，参数：" + id);
        try {
            if (id == null) {
                return DataResponse.no("参数不能为空");
            }
            SysConfigModel sysConfigModel = getSysConfigModel(id);
            ProductInnovateModel productInnovateModel = productInnovateFeign.getProductInnovateInfo(id).getDataWithException();
            logger.info(JSON.toJSONString("productInfo--" + productInnovateModel));
            return DataResponse.ok(sysConfigModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @Override
    public DataResponse<String> updateSysConfigInfo(@RequestBody Integer id) {
        logger.info("updateSysConfigInfo，参数：" + id);
        try {
            if (id == null) {
                return DataResponse.no("参数不能为空");
            }
            SysConfigModel sysConfigModel = new SysConfigModel();
            sysConfigModel.setId(id);
            int result = tccSysConfigService.updateConfigModel(null, sysConfigModel);
            logger.info(JSON.toJSONString("updateConfigModel--" + result));
            return DataResponse.ok("success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @Override
    public DataResponse<String> updateSysConfigInfoTest(@RequestBody Integer id) {
        logger.info("updateSysConfigInfoTest，参数：" + id);
        try {
            if (id == null) {
                return DataResponse.no("参数不能为空");
            }
            SysConfigModel sysConfigModel = new SysConfigModel();
            sysConfigModel.setId(id);
            int result = sysConfigService.updateConfigModelTest(sysConfigModel);
            logger.info(JSON.toJSONString("updateSysConfigInfoTest--" + result));
            return DataResponse.ok("success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    private SysConfigModel getSysConfigModel(Integer id) {
        SysConfigModel sysConfigModel = null;
        String key = "tradeInfoTest:" + id;
        String val = redisUtil.get(key);
        if (val == null) {
            sysConfigModel = sysConfigService.selectById(id);
            if (sysConfigModel != null) {
                String tmpVal = JSON.toJSONString(sysConfigModel);
                redisUtil.set(key, tmpVal);
            }
        } else {
            sysConfigModel = JSON.parseObject(val, SysConfigModel.class);
        }
        return sysConfigModel;
    }
}
