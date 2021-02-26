package com.hckj.product.microservice.service.blockQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 阻塞队列枚举表
 *
 * @author yuhui
 **/
public enum BlockQueueEnum {

    BLOCK_QUEUE_TEST("block_queue_test", "测试阻塞队列"),
    BLOCK_QUEUE_TEST2("block_queue_test2", "测试阻塞队列2"),
    ;

    private String code;
    private String desc;

    BlockQueueEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static boolean containCode(String code) {
        BlockQueueEnum[] enums = BlockQueueEnum.values();
        for (BlockQueueEnum tmpEnum : enums) {
            if (tmpEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> keys() {
        List<String> codeList = new ArrayList<>();
        BlockQueueEnum[] enums = BlockQueueEnum.values();
        for (BlockQueueEnum tmpEnum : enums) {
            codeList.add(tmpEnum.getCode());
        }
        return codeList;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
