package com.hckj.common.domain.order.condition;

import lombok.Data;

/**
 * 订单查询条件
 *
 * @author ：yuhui
 * @date ：Created in 2020/10/29 11:38
 */
@Data
public class OrderCondition {

    private Integer productId;

    private Integer sysConfigId;

    private Boolean isError;
}
