package com.hckj.common.practice.tomcat;

import java.io.File;
import java.net.URL;

import com.hckj.common.practice.tomcat.servlet.HelloServlet;
import com.hckj.common.practice.tomcat.servlet.TestServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;


public class TomcatStarter {

    public static void main(String[] args) throws LifecycleException {
        long start = System.currentTimeMillis();
        int port = 8888;
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        // 添加listener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);

        // 配置上下文
        String contextPath = "";
        String staticContextPath = "/static";
        // 配置根目录
        String docBase = TomcatStarter.class.getClassLoader().getResource("").getPath();
        // 配置页面目录
        String pagesPath = docBase + "pages";
        System.out.println("docBase:" + docBase + ",pages:" + pagesPath);

        String servletName1 = "hello";
        String servletMapping1 = "/hello";
        String servletName2 = "test";
        String servletMapping2 = "/test";
        // 配置静态目录访问权限
        tomcat.addWebapp(staticContextPath, pagesPath);

        Context context1 = tomcat.addWebapp(contextPath, docBase);
        tomcat.addServlet(contextPath, servletName1, new HelloServlet());
        context1.addServletMappingDecoded(servletMapping1, servletName1);
        tomcat.addServlet(contextPath, servletName2, new TestServlet());
        context1.addServletMappingDecoded(servletMapping2, servletName2);

        // 启动tomcat
        tomcat.start();
        long end = System.currentTimeMillis();
        System.out.println("启动完成,共使用了:" + (end - start) + "ms");
        // 进入监听状态,如果不进入监听状态,启动tomcat后就会关闭tomcat
        tomcat.getServer().await();
    }
}