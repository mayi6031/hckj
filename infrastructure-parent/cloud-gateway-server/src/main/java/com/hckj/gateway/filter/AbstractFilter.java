package com.hckj.gateway.filter;

import com.netflix.zuul.ZuulFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractFilter extends ZuulFilter {
    /**
     * 返回客户端消息
     *
     * @param response
     * @param byteData
     */
    protected void responseMessage(HttpServletResponse response, byte[] byteData) {
        response.setContentType("application/json; charset=utf8");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(byteData);
            outputStream.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
