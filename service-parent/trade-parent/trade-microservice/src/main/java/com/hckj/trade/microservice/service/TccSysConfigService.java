package com.hckj.trade.microservice.service;

import com.hckj.common.domain.trade.model.SysConfigModel;
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
public interface TccSysConfigService {

    /**
     * 定义两阶段提交
     * name = 为一阶段try方法
     * commitMethod = commit 为二阶段确认方法
     * rollbackMethod = rollback 为二阶段取消方法
     * BusinessActionContextParameter注解 可传递参数到二阶段方法
     *
     * @param sysConfigModel -入参
     * @return int
     */
    @TwoPhaseBusinessAction(name = "updateConfigModel", commitMethod = "commitUpdateConfigModelTcc", rollbackMethod = "rollbackUpdateConfigModelTcc")
    int updateConfigModel(BusinessActionContext businessActionContext, @BusinessActionContextParameter(paramName = "sysConfigModel") SysConfigModel sysConfigModel) throws Exception;

    /**
     * 二阶段确认方法、context可以传递try方法的参数
     *
     * @param context 上下文
     * @return int
     */
    boolean commitUpdateConfigModelTcc(BusinessActionContext context) throws Exception;

    /**
     * 二阶段取消方法、context可以传递try方法的参数
     *
     * @param context 上下文
     * @return int
     */
    boolean rollbackUpdateConfigModelTcc(BusinessActionContext context) throws Exception;

}
