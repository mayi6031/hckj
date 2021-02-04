package com.hckj.trade.microservice.service;

import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.common.exception.business.BusinessException;
import com.hckj.common.feign.product.ProductInnovateFeign;
import com.hckj.trade.microservice.dao.SysConfigDao;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配置信息Service
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/5 15:24
 */
@Service
public class SysConfigService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private ProductInnovateFeign productInnovateFeign;

    public SysConfigModel selectById(Integer id) {
        return sysConfigDao.selectByPrimaryKey(id);
    }

    public int updateConfigModel(SysConfigModel sysConfigModel) {
        int res = sysConfigDao.updateByPrimaryKeySelective(sysConfigModel);
        return res;
    }

    @GlobalTransactional(timeoutMills = 300000, name = "trade-service-tx", rollbackFor = Exception.class)
    public int updateConfigModelTest(SysConfigModel sysConfigModel) {
        sysConfigModel.setCode(System.currentTimeMillis() / 1000 + "");
        int res = sysConfigDao.updateByPrimaryKeySelective(sysConfigModel);
        String productRes = productInnovateFeign.updateProductInnovateInfoTest(sysConfigModel.getId()).getDataWithException();
        if (!"success".equals(productRes)) {
            throw new BusinessException("修改产品信息失败");
        }
        int a = 1;
        if (a == 1) {
            throw new BusinessException("定制抛出异常");
        }
        return res;
    }

}
