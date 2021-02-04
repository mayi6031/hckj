package com.hckj.trade.microservice.dao;


import com.hckj.common.domain.trade.model.SysConfigModel;

public interface SysConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfigModel record);

    int insertSelective(SysConfigModel record);

    SysConfigModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfigModel record);

    int updateByPrimaryKey(SysConfigModel record);
}