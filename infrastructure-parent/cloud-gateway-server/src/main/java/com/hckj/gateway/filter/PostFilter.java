package com.hckj.gateway.filter;

import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Description
 */
public class PostFilter extends AbstractFilter {
    private Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }


    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        InputStream zin = context.getResponseDataStream();
        if (zin == null) {
            responseMessage(response, "服务超时！".getBytes());
            return null;
        }
        try {
            byte[] resultByteArray = IOUtils.toByteArray(zin);
            String responseResult = new String(resultByteArray, StandardCharsets.UTF_8);
            long startTime = (long) context.get("beginTime");
            long consume = System.currentTimeMillis() - startTime;
            logger.info("{},响应完成,内容{},耗时--{}", request.getRequestURL().toString(), responseResult, consume);
            responseMessage(response, resultByteArray);
            return null;
        } catch (IOException e) {
            logger.error("出错了", e);
            responseMessage(response, "服务内部错误！".getBytes());
            return null;
        }
    }

}
