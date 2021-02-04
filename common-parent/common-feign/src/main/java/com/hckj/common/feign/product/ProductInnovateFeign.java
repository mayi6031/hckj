package com.hckj.common.feign.product;


import com.hckj.common.domain.product.model.ProductInnovateModel;
import com.hckj.common.feign.constants.MicroServiceIdConstant;
import com.hckj.common.web.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = MicroServiceIdConstant.PRODUCT_SERVICE, path = "/", fallback = ProductInnovateFeignFallback.class)
public interface ProductInnovateFeign {
    /**
     * 根据id获取产品信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getProductInnovateInfo", method = RequestMethod.POST)
    DataResponse<ProductInnovateModel> getProductInnovateInfo(@RequestBody Integer id);

    /**
     * 根据id获取产品信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sayHello", method = RequestMethod.POST)
    DataResponse<?> sayHello(@RequestBody Integer id);

    /**
     * 根据id修改产品code信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateProductInnovateInfo", method = RequestMethod.POST)
    DataResponse<String> updateProductInnovateInfo(@RequestBody Integer id);

    /**
     * 根据id修改产品code信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateProductInnovateInfoTest", method = RequestMethod.POST)
    DataResponse<String> updateProductInnovateInfoTest(@RequestBody Integer id);

}

@Component
class ProductInnovateFeignFallback implements ProductInnovateFeign {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInnovateFeignFallback.class);

    @Override
    public DataResponse<ProductInnovateModel> getProductInnovateInfo(@RequestBody Integer id) {
        LOGGER.error("=产品模块=根据id获取产品信息失败");
        return DataResponse.no("获取产品信息失败");
    }

    @Override
    public DataResponse<?> sayHello(Integer id) {
        LOGGER.error("=产品模块=sayHello失败");
        return DataResponse.no("sayHello失败");
    }

    @Override
    public DataResponse<String> updateProductInnovateInfo(Integer id) {
        LOGGER.error("=产品模块=updateProductInnovateInfo失败");
        return DataResponse.no("updateProductInnovateInfo失败");
    }

    @Override
    public DataResponse<String> updateProductInnovateInfoTest(Integer id) {
        LOGGER.error("=产品模块=updateProductInnovateInfoTest失败");
        return DataResponse.no("updateProductInnovateInfoTest失败");
    }

}