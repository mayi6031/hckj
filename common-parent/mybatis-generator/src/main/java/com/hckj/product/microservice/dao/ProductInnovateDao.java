package com.hckj.product.microservice.dao;

import com.hckj.common.domain.product.model.ProductInnovateModel;

public interface ProductInnovateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInnovateModel record);

    int insertSelective(ProductInnovateModel record);

    ProductInnovateModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInnovateModel record);

    int updateByPrimaryKey(ProductInnovateModel record);
}