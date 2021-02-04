package com.hckj.test;
 
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class MyWorkStart {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
 
    //启动流程
    @Test
    public void startProcess(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_custom");//流程的名称，对应流程定义表的key字段，也可以使用ByID来启动流程
        System.out.println(processInstance.getId());
    }
 
}