package com.hckj.product.microservice.dao;

import com.hckj.common.domain.product.model.SysConfigModel;

public interface SysConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfigModel record);

    int insertSelective(SysConfigModel record);

    SysConfigModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfigModel record);

    int updateByPrimaryKey(SysConfigModel record);
}