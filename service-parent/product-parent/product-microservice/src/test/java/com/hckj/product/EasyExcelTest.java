package com.hckj.product;

import com.alibaba.fastjson.JSON;
import com.hckj.common.util.easyexcel.EasyExcelStatHelp;
import com.hckj.common.util.easyexcel.EasyExcelUtil;
import com.hckj.product.dto.PreData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * EasyExcel测试类
 *
 * @author ：yuhui
 * @date ：Created in 2021/2/2 18:26
 */
public class EasyExcelTest {
    private static CopyOnWriteArrayList<EasyExcelStatHelp> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        long now = System.currentTimeMillis();
        String readFilename = "f:/opt/存续列表bak.xlsx";

        EasyExcelUtil.readExcel(new BufferedInputStream(new FileInputStream(readFilename)), PreData.class, 2, callbackEasyExcelStatHelp -> {
            System.out.println(callbackEasyExcelStatHelp.getSucRecordCount());
            copyOnWriteArrayList.add(callbackEasyExcelStatHelp);
        }, 100);
        System.out.println("ok,consume:" + (System.currentTimeMillis() - now) + "," + JSON.toJSONString(copyOnWriteArrayList));
    }
}
