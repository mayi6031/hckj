package com.hsgd.sr.microservice.partner.dao;

import com.hsgd.common.domain.customer.partner.model.HsgdPartnerSmsRecord;

public interface HsgdPartnerSmsRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(HsgdPartnerSmsRecord record);

    int insertSelective(HsgdPartnerSmsRecord record);

    HsgdPartnerSmsRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HsgdPartnerSmsRecord record);

    int updateByPrimaryKey(HsgdPartnerSmsRecord record);
}