package com.hckj.common.domain.product.model;

import java.math.BigDecimal;

public class ProductInnovateModel {
    private Integer id;

    private String code;

    private String name;

    private String manager;

    private Byte isScaled;

    private Byte isTest;

    private Byte isWdl;

    private BigDecimal scaleAmount;

    private BigDecimal scaleAlarmAmount;

    private BigDecimal hasInvestAmount;

    private BigDecimal remainAmount;

    private BigDecimal minPurchaseAmount;

    private BigDecimal increaseAmount;

    private Integer scalePersion;

    private Integer scaleAlarmPersion;

    private Byte source;

    private Integer productTypeId;

    private Byte achievementType;

    private Byte attribute;

    private String period;

    private String periodRemark;

    private String riskLevel;

    private String allowRiskEvaluate;

    private String investCategory;

    private String hightlights;

    private Byte isExceedPay;

    private BigDecimal exceedPayAmount;

    private BigDecimal lfFoldRatio;

    private BigDecimal pxFoldRatio;

    private BigDecimal commissionProportion;

    private String productReview;

    private String riskPrompt;

    private Byte productState;

    private Byte auditState;

    private Long onlineTime;

    private Long establishTime;

    private Long raiseEndTime;

    private Long terminatePublishTime;

    private Long expireTime;

    private Long liquidationTime;

    private Long cashTime;

    private Long createTime;

    private Byte yn;

    private String hightlights2;

    private String hightlights3;

    private Byte currency;

    private Byte liquidationAuditState;

    private String closedPeriod;

    private Integer parentProductTypeId;

    private String productReview2;

    private String productReview3;

    private String productReview4;

    private String productReview5;

    private Byte isrecover;

    private String backImgForAndroid;

    private String backImgForIos;

    private Byte raiseType;

    private Byte isAppDisplay;

    private Byte contractSignatureState;

    private Byte processNew;

    private Byte isPublic;

    private Integer productTagId;

    private String investmentTrack;

    private String investmentLogic;

    private Byte isDualRecord;

    private String productPublishTip;

    private Integer managerId;

    private Integer jfProductTypeId;

    private Integer jfParentProductTypeId;

    private String contractPre;

    private Byte isElectronicContract;

    private Byte riskEvaluationModel;

    private Byte isNeedAssetCertification;

    private Byte productMark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public Byte getIsScaled() {
        return isScaled;
    }

    public void setIsScaled(Byte isScaled) {
        this.isScaled = isScaled;
    }

    public Byte getIsTest() {
        return isTest;
    }

    public void setIsTest(Byte isTest) {
        this.isTest = isTest;
    }

    public Byte getIsWdl() {
        return isWdl;
    }

    public void setIsWdl(Byte isWdl) {
        this.isWdl = isWdl;
    }

    public BigDecimal getScaleAmount() {
        return scaleAmount;
    }

    public void setScaleAmount(BigDecimal scaleAmount) {
        this.scaleAmount = scaleAmount;
    }

    public BigDecimal getScaleAlarmAmount() {
        return scaleAlarmAmount;
    }

    public void setScaleAlarmAmount(BigDecimal scaleAlarmAmount) {
        this.scaleAlarmAmount = scaleAlarmAmount;
    }

    public BigDecimal getHasInvestAmount() {
        return hasInvestAmount;
    }

    public void setHasInvestAmount(BigDecimal hasInvestAmount) {
        this.hasInvestAmount = hasInvestAmount;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public BigDecimal getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(BigDecimal increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public Integer getScalePersion() {
        return scalePersion;
    }

    public void setScalePersion(Integer scalePersion) {
        this.scalePersion = scalePersion;
    }

    public Integer getScaleAlarmPersion() {
        return scaleAlarmPersion;
    }

    public void setScaleAlarmPersion(Integer scaleAlarmPersion) {
        this.scaleAlarmPersion = scaleAlarmPersion;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Byte getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(Byte achievementType) {
        this.achievementType = achievementType;
    }

    public Byte getAttribute() {
        return attribute;
    }

    public void setAttribute(Byte attribute) {
        this.attribute = attribute;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getPeriodRemark() {
        return periodRemark;
    }

    public void setPeriodRemark(String periodRemark) {
        this.periodRemark = periodRemark == null ? null : periodRemark.trim();
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    public String getAllowRiskEvaluate() {
        return allowRiskEvaluate;
    }

    public void setAllowRiskEvaluate(String allowRiskEvaluate) {
        this.allowRiskEvaluate = allowRiskEvaluate == null ? null : allowRiskEvaluate.trim();
    }

    public String getInvestCategory() {
        return investCategory;
    }

    public void setInvestCategory(String investCategory) {
        this.investCategory = investCategory == null ? null : investCategory.trim();
    }

    public String getHightlights() {
        return hightlights;
    }

    public void setHightlights(String hightlights) {
        this.hightlights = hightlights == null ? null : hightlights.trim();
    }

    public Byte getIsExceedPay() {
        return isExceedPay;
    }

    public void setIsExceedPay(Byte isExceedPay) {
        this.isExceedPay = isExceedPay;
    }

    public BigDecimal getExceedPayAmount() {
        return exceedPayAmount;
    }

    public void setExceedPayAmount(BigDecimal exceedPayAmount) {
        this.exceedPayAmount = exceedPayAmount;
    }

    public BigDecimal getLfFoldRatio() {
        return lfFoldRatio;
    }

    public void setLfFoldRatio(BigDecimal lfFoldRatio) {
        this.lfFoldRatio = lfFoldRatio;
    }

    public BigDecimal getPxFoldRatio() {
        return pxFoldRatio;
    }

    public void setPxFoldRatio(BigDecimal pxFoldRatio) {
        this.pxFoldRatio = pxFoldRatio;
    }

    public BigDecimal getCommissionProportion() {
        return commissionProportion;
    }

    public void setCommissionProportion(BigDecimal commissionProportion) {
        this.commissionProportion = commissionProportion;
    }

    public String getProductReview() {
        return productReview;
    }

    public void setProductReview(String productReview) {
        this.productReview = productReview == null ? null : productReview.trim();
    }

    public String getRiskPrompt() {
        return riskPrompt;
    }

    public void setRiskPrompt(String riskPrompt) {
        this.riskPrompt = riskPrompt == null ? null : riskPrompt.trim();
    }

    public Byte getProductState() {
        return productState;
    }

    public void setProductState(Byte productState) {
        this.productState = productState;
    }

    public Byte getAuditState() {
        return auditState;
    }

    public void setAuditState(Byte auditState) {
        this.auditState = auditState;
    }

    public Long getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Long onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Long getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Long establishTime) {
        this.establishTime = establishTime;
    }

    public Long getRaiseEndTime() {
        return raiseEndTime;
    }

    public void setRaiseEndTime(Long raiseEndTime) {
        this.raiseEndTime = raiseEndTime;
    }

    public Long getTerminatePublishTime() {
        return terminatePublishTime;
    }

    public void setTerminatePublishTime(Long terminatePublishTime) {
        this.terminatePublishTime = terminatePublishTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getLiquidationTime() {
        return liquidationTime;
    }

    public void setLiquidationTime(Long liquidationTime) {
        this.liquidationTime = liquidationTime;
    }

    public Long getCashTime() {
        return cashTime;
    }

    public void setCashTime(Long cashTime) {
        this.cashTime = cashTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }

    public String getHightlights2() {
        return hightlights2;
    }

    public void setHightlights2(String hightlights2) {
        this.hightlights2 = hightlights2 == null ? null : hightlights2.trim();
    }

    public String getHightlights3() {
        return hightlights3;
    }

    public void setHightlights3(String hightlights3) {
        this.hightlights3 = hightlights3 == null ? null : hightlights3.trim();
    }

    public Byte getCurrency() {
        return currency;
    }

    public void setCurrency(Byte currency) {
        this.currency = currency;
    }

    public Byte getLiquidationAuditState() {
        return liquidationAuditState;
    }

    public void setLiquidationAuditState(Byte liquidationAuditState) {
        this.liquidationAuditState = liquidationAuditState;
    }

    public String getClosedPeriod() {
        return closedPeriod;
    }

    public void setClosedPeriod(String closedPeriod) {
        this.closedPeriod = closedPeriod == null ? null : closedPeriod.trim();
    }

    public Integer getParentProductTypeId() {
        return parentProductTypeId;
    }

    public void setParentProductTypeId(Integer parentProductTypeId) {
        this.parentProductTypeId = parentProductTypeId;
    }

    public String getProductReview2() {
        return productReview2;
    }

    public void setProductReview2(String productReview2) {
        this.productReview2 = productReview2 == null ? null : productReview2.trim();
    }

    public String getProductReview3() {
        return productReview3;
    }

    public void setProductReview3(String productReview3) {
        this.productReview3 = productReview3 == null ? null : productReview3.trim();
    }

    public String getProductReview4() {
        return productReview4;
    }

    public void setProductReview4(String productReview4) {
        this.productReview4 = productReview4 == null ? null : productReview4.trim();
    }

    public String getProductReview5() {
        return productReview5;
    }

    public void setProductReview5(String productReview5) {
        this.productReview5 = productReview5 == null ? null : productReview5.trim();
    }

    public Byte getIsrecover() {
        return isrecover;
    }

    public void setIsrecover(Byte isrecover) {
        this.isrecover = isrecover;
    }

    public String getBackImgForAndroid() {
        return backImgForAndroid;
    }

    public void setBackImgForAndroid(String backImgForAndroid) {
        this.backImgForAndroid = backImgForAndroid == null ? null : backImgForAndroid.trim();
    }

    public String getBackImgForIos() {
        return backImgForIos;
    }

    public void setBackImgForIos(String backImgForIos) {
        this.backImgForIos = backImgForIos == null ? null : backImgForIos.trim();
    }

    public Byte getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(Byte raiseType) {
        this.raiseType = raiseType;
    }

    public Byte getIsAppDisplay() {
        return isAppDisplay;
    }

    public void setIsAppDisplay(Byte isAppDisplay) {
        this.isAppDisplay = isAppDisplay;
    }

    public Byte getContractSignatureState() {
        return contractSignatureState;
    }

    public void setContractSignatureState(Byte contractSignatureState) {
        this.contractSignatureState = contractSignatureState;
    }

    public Byte getProcessNew() {
        return processNew;
    }

    public void setProcessNew(Byte processNew) {
        this.processNew = processNew;
    }

    public Byte getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Byte isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getProductTagId() {
        return productTagId;
    }

    public void setProductTagId(Integer productTagId) {
        this.productTagId = productTagId;
    }

    public String getInvestmentTrack() {
        return investmentTrack;
    }

    public void setInvestmentTrack(String investmentTrack) {
        this.investmentTrack = investmentTrack == null ? null : investmentTrack.trim();
    }

    public String getInvestmentLogic() {
        return investmentLogic;
    }

    public void setInvestmentLogic(String investmentLogic) {
        this.investmentLogic = investmentLogic == null ? null : investmentLogic.trim();
    }

    public Byte getIsDualRecord() {
        return isDualRecord;
    }

    public void setIsDualRecord(Byte isDualRecord) {
        this.isDualRecord = isDualRecord;
    }

    public String getProductPublishTip() {
        return productPublishTip;
    }

    public void setProductPublishTip(String productPublishTip) {
        this.productPublishTip = productPublishTip == null ? null : productPublishTip.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getJfProductTypeId() {
        return jfProductTypeId;
    }

    public void setJfProductTypeId(Integer jfProductTypeId) {
        this.jfProductTypeId = jfProductTypeId;
    }

    public Integer getJfParentProductTypeId() {
        return jfParentProductTypeId;
    }

    public void setJfParentProductTypeId(Integer jfParentProductTypeId) {
        this.jfParentProductTypeId = jfParentProductTypeId;
    }

    public String getContractPre() {
        return contractPre;
    }

    public void setContractPre(String contractPre) {
        this.contractPre = contractPre == null ? null : contractPre.trim();
    }

    public Byte getIsElectronicContract() {
        return isElectronicContract;
    }

    public void setIsElectronicContract(Byte isElectronicContract) {
        this.isElectronicContract = isElectronicContract;
    }

    public Byte getRiskEvaluationModel() {
        return riskEvaluationModel;
    }

    public void setRiskEvaluationModel(Byte riskEvaluationModel) {
        this.riskEvaluationModel = riskEvaluationModel;
    }

    public Byte getIsNeedAssetCertification() {
        return isNeedAssetCertification;
    }

    public void setIsNeedAssetCertification(Byte isNeedAssetCertification) {
        this.isNeedAssetCertification = isNeedAssetCertification;
    }

    public Byte getProductMark() {
        return productMark;
    }

    public void setProductMark(Byte productMark) {
        this.productMark = productMark;
    }
}