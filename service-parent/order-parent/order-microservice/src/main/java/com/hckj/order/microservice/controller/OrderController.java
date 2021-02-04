package com.hckj.order.microservice.controller;

import com.hckj.common.domain.order.condition.OrderCondition;
import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.common.web.DataResponse;
import com.hckj.order.microservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单信息Controller
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/5 15:26
 */
@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
    public DataResponse<Map<String, Object>> getOrderInfo(@RequestBody OrderCondition orderCondition) {
        logger.info("getOrderInfo，参数：" + orderCondition);
        try {
            if (orderCondition == null) {
                return DataResponse.no("参数不能为空");
            }
            ProductInnovateModel productInnovateModel = orderService.getProductInnovateInfo(orderCondition.getProductId());

            SysConfigModel sysConfigModel = orderService.getSysConfigInfo(orderCondition.getSysConfigId());

            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("productInnovate", productInnovateModel);
            resultMap.put("sysConfig", sysConfigModel);
            return DataResponse.ok(resultMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @RequestMapping(value = "/getOrderInfoTest", method = RequestMethod.POST)
    public DataResponse<SysConfigModel> getOrderInfoTest(@RequestBody OrderCondition orderCondition) {
        logger.info("getOrderInfo，参数：" + orderCondition);
        try {
            if (orderCondition == null) {
                return DataResponse.no("参数不能为空");
            }
            SysConfigModel sysConfigModel = orderService.getSysConfigInfoTest(orderCondition.getSysConfigId());
            return DataResponse.ok(sysConfigModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateOrderInfo", method = RequestMethod.POST)
    public DataResponse<String> updateOrderInfo(@RequestBody OrderCondition orderCondition) {
        logger.info("updateOrderInfo，参数：" + orderCondition);
        try {
            if (orderCondition == null) {
                return DataResponse.no("参数不能为空");
            }
            orderService.updateOrderInfo(orderCondition);
            return DataResponse.ok("success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return DataResponse.no(e.getMessage());
        }
    }
}
