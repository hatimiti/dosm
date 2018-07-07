package com.github.hatimiti.dosm.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CmKaisha extends Entity {

    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    /** CM_KAISHA_ID: {PK, ID, NotNull, BIGINT(19)} */
    private Long cmKaishaId;
    /** KAISHA_MEI: {UQ, NotNull, VARCHAR(50)} */
    private String kaishaMei;
    /** KAISHA_MEI_EN: {VARCHAR(100)} */
    private String kaishaMeiEn;

//    public void copyFrom(final CmKaishaForm form) {
//        this.setCmKaishaId(form.getCmKaishaId());
//        this.setKaishaMei(form.getKaishaMei());
//        this.setKaishaMeiEn(form.getKaishaMeiEn());
//        this.setVersionNo(form.getVersionNo());
//    }
}
