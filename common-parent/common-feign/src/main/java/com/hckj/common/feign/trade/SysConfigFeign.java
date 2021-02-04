package com.hckj.common.feign.trade;


import com.hckj.common.domain.trade.model.SysConfigModel;
import com.hckj.common.feign.constants.MicroServiceIdConstant;
import com.hckj.common.web.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = MicroServiceIdConstant.TRADE_SERVICE, path = "/", fallback = SysConfigFeignFallback.class)
public interface SysConfigFeign {
    /**
     * 根据id获取配置信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getSysConfigInfo", method = RequestMethod.POST)
    DataResponse<SysConfigModel> getSysConfigInfo(@RequestBody Integer id);

    /**
     * 根据id获取配置信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getSysConfigInfoTest", method = RequestMethod.POST)
    DataResponse<SysConfigModel> getSysConfigInfoTest(@RequestBody Integer id);

    /**
     * 根据id修改配置code信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSysConfigInfo", method = RequestMethod.POST)
    DataResponse<String> updateSysConfigInfo(@RequestBody Integer id);

    /**
     * 根据id修改配置code信息Test
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSysConfigInfoTest", method = RequestMethod.POST)
    DataResponse<String> updateSysConfigInfoTest(@RequestBody Integer id);

}

@Component
class SysConfigFeignFallback implements SysConfigFeign {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysConfigFeignFallback.class);

    @Override
    public DataResponse<SysConfigModel> getSysConfigInfo(@RequestBody Integer id) {
        LOGGER.error("=交易模块=根据id获取配置信息失败");
        return DataResponse.no("获取配置信息失败");
    }

    @Override
    public DataResponse<SysConfigModel> getSysConfigInfoTest(@RequestBody Integer id) {
        LOGGER.error("=交易模块=根据id获取配置信息2失败");
        return DataResponse.no("获取配置信息2失败");
    }

    @Override
    public DataResponse<String> updateSysConfigInfo(Integer id) {
        LOGGER.error("=交易模块=根据id修改配置code失败");
        return DataResponse.no("根据id修改配置code失败");
    }

    @Override
    public DataResponse<String> updateSysConfigInfoTest(Integer id) {
        LOGGER.error("=交易模块=根据id修改配置codeTest失败");
        return DataResponse.no("根据id修改配置codeTest失败");
    }

}