package com.hckj.product.microservice.service.impl;

import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.product.microservice.dao.ProductInnovateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产品信息Service
 *
 * @author ：yuhui
 * @date ：Created in 2020/8/5 15:24
 */
@Service
public class ProductInnovateService {

    @Autowired
    private ProductInnovateDao productInnovateDao;

    public ProductInnovateModel selectById(Integer id) {
        return productInnovateDao.selectByPrimaryKey(id);
    }

    public int updateProductInnovate(ProductInnovateModel productInnovateModel) {
        return productInnovateDao.updateByPrimaryKeySelective(productInnovateModel);
    }
}
