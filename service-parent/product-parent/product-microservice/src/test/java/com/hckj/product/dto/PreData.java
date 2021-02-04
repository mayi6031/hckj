package com.hckj.product.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

//设置表头和添加的数据字段

@Data
public class PreData {

    @ExcelProperty(value = "序号", index = 0)
    private Integer no;

    @ExcelProperty(value = "预约单号", index = 1)
    private String orderNo;

    @ExcelProperty(value = "产品名称", index = 2)
    private String productName;

    @ExcelProperty(value = "产品大类", index = 3)
    private String productCategory;

    @ExcelProperty(value = "产品子类", index = 4)
    private String productType;

    @ExcelProperty(value = "产品系列", index = 5)
    private String productSerial;

    @ExcelProperty(value = "募集类型", index = 6)
    private String raiseType;

    @ExcelProperty(value = "客户姓名", index = 7)
    private String customerName;

    @ExcelProperty(value = "证件类型", index = 8)
    private String customerCertType;

    @ExcelProperty(value = "客户证件号码", index = 9)
    private String customerCertNo;

    @ExcelProperty(value = "客户联系电话", index = 10)
    private String customerPhone;

    @ExcelProperty(value = "客户邮箱", index = 11)
    private String customerEmail;

    @ExcelProperty(value = "客户地址", index = 12)
    private String customerAddress;

    @ExcelProperty(value = "认购金额(元)", index = 13)
    private BigDecimal customerPurchaseAmt;

    @Override
    public String toString() {
        return "PreData{" +
                "no='" + no + '\'' +
                ", orderNo=" + orderNo +
                ", productName=" + productName +
                ", productCategory=" + productCategory +
                ", productType=" + productType +
                ", productSerial=" + productSerial +
                ", raiseType=" + raiseType +
                ", customerName=" + customerName +
                ", customerCertType=" + customerCertType +
                ", customerCertNo=" + customerCertNo +
                ", customerPhone=" + customerPhone +
                ", customerEmail=" + customerEmail +
                ", customerAddress=" + customerAddress +
                ", customerPurchaseAmt=" + customerPurchaseAmt +
                '}';
    }
}
