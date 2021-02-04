package com.hckj.product.microservice.service;

import com.hckj.common.domain.product.model.ProductInnovateModel;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * Tcc接口
 *
 * @author ：yuhui
 * @date ：Created in 2021/1/28 13:59
 */
@LocalTCC
public interface TccProductInnovateService {

    /**
     * 定义两阶段提交
     * name = 为一阶段try方法
     * commitMethod = commit 为二阶段确认方法
     * rollbackMethod = rollback 为二阶段取消方法
     * BusinessActionContextParameter注解 可传递参数到二阶段方法
     *
     * @param productInnovateModel -入参
     * @return int
     */
    @TwoPhaseBusinessAction(name = "updateConfigModel", commitMethod = "commitUpdateProductInnovateTcc", rollbackMethod = "rollbackUpdateProductInnovateTcc")
    int updateProductInnovate(BusinessActionContext businessActionContext, @BusinessActionContextParameter(paramName = "productInnovateModel") ProductInnovateModel productInnovateModel) throws Exception;

    /**
     * 二阶段确认方法、context可以传递try方法的参数
     *
     * @param context 上下文
     * @return int
     */
    boolean commitUpdateProductInnovateTcc(BusinessActionContext context) throws Exception;

    /**
     * 二阶段取消方法、context可以传递try方法的参数
     *
     * @param context 上下文
     * @return int
     */
    boolean rollbackUpdateProductInnovateTcc(BusinessActionContext context) throws Exception;

}
