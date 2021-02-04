package com.hsgd.common.domain.customer.partner.model;

import java.util.Date;

public class HsgdPartnerSmsRecord {
    private Long id;

    private Integer subjectRole;

    private String subjectName;

    private Long subjectCustomerId;

    private String relateVtcCustomerId;

    private String relateVtcName;

    private String relateVtcMobile;

    private String relateSrCustomerId;

    private String relateSrName;

    private String relateSrMobile;

    private Long partnerCustomerId;

    private String partnerName;

    private String partnerMobile;

    private Long regionManagerCustomerId;

    private String regionManagerName;

    private String regionManagerMobile;

    private Long regionChiefCustomerId;

    private String regionChiefName;

    private String regionChiefMobile;

    private Date created;

    private String createdBy;

    private Date updated;

    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSubjectRole() {
        return subjectRole;
    }

    public void setSubjectRole(Integer subjectRole) {
        this.subjectRole = subjectRole;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public Long getSubjectCustomerId() {
        return subjectCustomerId;
    }

    public void setSubjectCustomerId(Long subjectCustomerId) {
        this.subjectCustomerId = subjectCustomerId;
    }

    public String getRelateVtcCustomerId() {
        return relateVtcCustomerId;
    }

    public void setRelateVtcCustomerId(String relateVtcCustomerId) {
        this.relateVtcCustomerId = relateVtcCustomerId == null ? null : relateVtcCustomerId.trim();
    }

    public String getRelateVtcName() {
        return relateVtcName;
    }

    public void setRelateVtcName(String relateVtcName) {
        this.relateVtcName = relateVtcName == null ? null : relateVtcName.trim();
    }

    public String getRelateVtcMobile() {
        return relateVtcMobile;
    }

    public void setRelateVtcMobile(String relateVtcMobile) {
        this.relateVtcMobile = relateVtcMobile == null ? null : relateVtcMobile.trim();
    }

    public String getRelateSrCustomerId() {
        return relateSrCustomerId;
    }

    public void setRelateSrCustomerId(String relateSrCustomerId) {
        this.relateSrCustomerId = relateSrCustomerId == null ? null : relateSrCustomerId.trim();
    }

    public String getRelateSrName() {
        return relateSrName;
    }

    public void setRelateSrName(String relateSrName) {
        this.relateSrName = relateSrName == null ? null : relateSrName.trim();
    }

    public String getRelateSrMobile() {
        return relateSrMobile;
    }

    public void setRelateSrMobile(String relateSrMobile) {
        this.relateSrMobile = relateSrMobile == null ? null : relateSrMobile.trim();
    }

    public Long getPartnerCustomerId() {
        return partnerCustomerId;
    }

    public void setPartnerCustomerId(Long partnerCustomerId) {
        this.partnerCustomerId = partnerCustomerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getPartnerMobile() {
        return partnerMobile;
    }

    public void setPartnerMobile(String partnerMobile) {
        this.partnerMobile = partnerMobile == null ? null : partnerMobile.trim();
    }

    public Long getRegionManagerCustomerId() {
        return regionManagerCustomerId;
    }

    public void setRegionManagerCustomerId(Long regionManagerCustomerId) {
        this.regionManagerCustomerId = regionManagerCustomerId;
    }

    public String getRegionManagerName() {
        return regionManagerName;
    }

    public void setRegionManagerName(String regionManagerName) {
        this.regionManagerName = regionManagerName == null ? null : regionManagerName.trim();
    }

    public String getRegionManagerMobile() {
        return regionManagerMobile;
    }

    public void setRegionManagerMobile(String regionManagerMobile) {
        this.regionManagerMobile = regionManagerMobile == null ? null : regionManagerMobile.trim();
    }

    public Long getRegionChiefCustomerId() {
        return regionChiefCustomerId;
    }

    public void setRegionChiefCustomerId(Long regionChiefCustomerId) {
        this.regionChiefCustomerId = regionChiefCustomerId;
    }

    public String getRegionChiefName() {
        return regionChiefName;
    }

    public void setRegionChiefName(String regionChiefName) {
        this.regionChiefName = regionChiefName == null ? null : regionChiefName.trim();
    }

    public String getRegionChiefMobile() {
        return regionChiefMobile;
    }

    public void setRegionChiefMobile(String regionChiefMobile) {
        this.regionChiefMobile = regionChiefMobile == null ? null : regionChiefMobile.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }
}