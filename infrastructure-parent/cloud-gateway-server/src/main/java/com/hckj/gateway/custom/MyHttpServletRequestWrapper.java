package com.hckj.gateway.custom;

import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * created by yuhui
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] bodyByte;

    public MyHttpServletRequestWrapper(HttpServletRequest request, byte[] reqBodyBytes) {
        super(request);
        bodyByte = reqBodyBytes;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStreamWrapper(bodyByte);
    }

    @Override
    public int getContentLength() {
        return bodyByte.length;
    }

    @Override
    public long getContentLengthLong() {
        return bodyByte.length;
    }
}