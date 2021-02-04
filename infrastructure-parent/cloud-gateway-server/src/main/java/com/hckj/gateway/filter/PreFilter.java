package com.hckj.gateway.filter;

import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class PreFilter extends AbstractFilter {
    private static Logger logger = LoggerFactory.getLogger(PreFilter.class);

    /**
     * 前置过滤器。
     * <p>
     * 但是在 zuul 中定义了四种不同生命周期的过滤器类型：
     * <p>
     * 1、pre：可以在请求被路由之前调用；
     * <p>
     * 2、route：在路由请求时候被调用；
     * <p>
     * 3、post：在route和error过滤器之后被调用；
     * <p>
     * 4、error：处理请求时发生错误时被调用；
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的优先级，数字越大，优先级越低。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器。
     * <p>
     * true：说明需要过滤；
     * <p>
     * false：说明不要过滤；
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。
     *
     * @return
     */
    @Override
    public Object run() {
        long startTime = System.currentTimeMillis();
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.set("beginTime", startTime);
        HttpServletRequest servletRequest = requestContext.getRequest();
        logger.info("send {} request to {}", servletRequest.getMethod(), servletRequest.getRequestURL().toString());
        try {
            if ("GET".equals(servletRequest.getMethod())) {
                // TODO
            } else {

            }
        } catch (Exception e) {
            logger.error("解密报文失败,", e);
            requestContext.setSendZuulResponse(false);
            responseMessage(requestContext.getResponse(), "解密失败！".getBytes());
            return null;
        }
        requestContext.setSendZuulResponse(true);
        return null;
    }


}
