package com.hckj.trade.microservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.hckj.common.cache.redis.RedisUtil;
import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.common.exception.business.BusinessException;
import com.hckj.trade.microservice.dao.SysConfigDao;
import com.hckj.trade.microservice.service.TccSysConfigService;
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
public class TccSysConfigServiceImpl implements TccSysConfigService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String SYSCONFIG_PREFIX = "sysConfig-";

    @Autowired
    private SysConfigDao sysConfigDao;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int updateConfigModel(BusinessActionContext businessActionContext, SysConfigModel sysConfigModel) throws Exception {
        SysConfigModel oldSysConfig = sysConfigDao.selectByPrimaryKey(sysConfigModel.getId());
        if (oldSysConfig == null) {
            logger.error("该配置信息不存在：" + oldSysConfig.getId());
            throw new BusinessException("该配置信息不存在:" + sysConfigModel.getId());
        }
        String key = SYSCONFIG_PREFIX + sysConfigModel.getId();
        redisUtil.setnx(key, oldSysConfig.getCode(), 10);
        sysConfigModel.setCode(System.currentTimeMillis() / 1000 + "");
        int res = sysConfigDao.updateByPrimaryKeySelective(sysConfigModel);
        logger.info("一阶段准备影响行数：" + res);
        return res;
    }

    @Override
    public boolean commitUpdateConfigModelTcc(BusinessActionContext context) throws Exception {
        SysConfigModel sysConfigModel = JSON.parseObject(JSON.toJSONString(context.getActionContext("sysConfigModel")), SysConfigModel.class);
        if (sysConfigModel == null) {
            logger.error("二阶段提交失败");
            return false;
        }
        String key = SYSCONFIG_PREFIX + sysConfigModel.getId();
        redisUtil.del(key);
        logger.info("二阶段提交");
        return true;
    }

    @Override
    public boolean rollbackUpdateConfigModelTcc(BusinessActionContext context) throws Exception {
        SysConfigModel sysConfigModel = JSON.parseObject(JSON.toJSONString(context.getActionContext("sysConfigModel")), SysConfigModel.class);
        if (sysConfigModel == null) {
            logger.error("二阶段回滚失败");
            return false;
        }
        String key = SYSCONFIG_PREFIX + sysConfigModel.getId();
        String oldCode = redisUtil.get(key);
        if(oldCode == null){
            oldCode = "default";
        }
        sysConfigModel.setCode(oldCode);
        int res = sysConfigDao.updateByPrimaryKeySelective(sysConfigModel);
        logger.info("二阶段回滚影响行数：" + res);
        return true;
    }
}
