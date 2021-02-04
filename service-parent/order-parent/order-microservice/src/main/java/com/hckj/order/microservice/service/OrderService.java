package com.hckj.order.microservice.service;

import com.hckj.common.domain.order.condition.OrderCondition;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.common.exception.business.BusinessException;
import com.hckj.common.feign.product.ProductInnovateFeign;
import com.hckj.common.feign.trade.SysConfigFeign;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单Service
 *
 * @author ：yuhui
 * @date ：Created in 2021/1/27 14:52
 */
@Service
public class OrderService {
    @Autowired
    private ProductInnovateFeign productInnovateFeign;

    @Autowired
    private SysConfigFeign sysConfigFeign;

    public ProductInnovateModel getProductInnovateInfo(Integer productId) {
        ProductInnovateModel productInnovateModel = productInnovateFeign.getProductInnovateInfo(productId).getDataWithException();
        return productInnovateModel;
    }

    public SysConfigModel getSysConfigInfo(Integer sysConfigId) {
        SysConfigModel sysConfigModel = sysConfigFeign.getSysConfigInfo(sysConfigId).getDataWithException();
        return sysConfigModel;
    }

    public SysConfigModel getSysConfigInfoTest(Integer sysConfigId) {
        SysConfigModel sysConfigModel = sysConfigFeign.getSysConfigInfoTest(sysConfigId).getDataWithException();
        return sysConfigModel;
    }

    @GlobalTransactional(timeoutMills = 300000, name = "order-service-tx", rollbackFor = Exception.class)
    public void updateOrderInfo(OrderCondition orderCondition) {
        String sysConfigRes = sysConfigFeign.updateSysConfigInfo(orderCondition.getSysConfigId()).getDataWithException();
        if (!"success".equals(sysConfigRes)) {
            throw new BusinessException("修改配置信息失败");
        }
        String productRes = productInnovateFeign.updateProductInnovateInfo(orderCondition.getProductId()).getDataWithException();
        if (!"success".equals(productRes)) {
            throw new BusinessException("修改产品信息失败");
        }
        if (orderCondition.getIsError() != null && orderCondition.getIsError()) {
            throw new BusinessException("定制抛出异常");
        }
    }
}
