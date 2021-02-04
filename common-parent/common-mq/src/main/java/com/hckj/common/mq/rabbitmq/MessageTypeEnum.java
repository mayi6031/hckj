package com.hckj.common.mq.rabbitmq;

/**
 * 消息类型枚举
 */
public enum MessageTypeEnum {

    PRODUCT_CREATE(1, "产品创建"),
    PRODUCT_ONLINE(2, "产品上线");

    private Integer code;
    private String name;

    private MessageTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MessageTypeEnum getMessageTypeEnum(Integer code) {
        if (null == code) {
            return null;
        }
        for (MessageTypeEnum roleEnum : values()) {
            if (roleEnum.getCode().equals(code)) {
                return roleEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
