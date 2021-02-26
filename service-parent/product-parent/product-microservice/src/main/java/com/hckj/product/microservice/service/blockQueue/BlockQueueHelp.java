package com.hckj.product.microservice.service.blockQueue;

/**
 * 阻塞队列辅助表
 *
 * @author yuhui
 **/
public class BlockQueueHelp {

    private BlockQueueEnum blockQueueEnum;
    private String value;

    public BlockQueueHelp() {

    }

    public BlockQueueHelp(BlockQueueEnum blockQueueEnum, String value) {
        this.blockQueueEnum = blockQueueEnum;
        this.value = value;
    }

    public BlockQueueEnum getBlockQueueEnum() {
        return blockQueueEnum;
    }

    public void setBlockQueueEnum(BlockQueueEnum blockQueueEnum) {
        this.blockQueueEnum = blockQueueEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
