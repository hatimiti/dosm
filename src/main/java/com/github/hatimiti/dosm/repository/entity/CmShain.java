package com.github.hatimiti.dosm.repository.entity;

import com.github.hatimiti.dosm.ad.master.cmshain.CmShainForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class CmShain extends Entity {

    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    /** CM_SHAIN_ID: {PK, ID, NotNull, BIGINT(19)} */
    private Long cmShainId;
    /** CM_KAISHA_ID: {NotNull, BIGINT(19), FK to cm_kaisha} */
    private Long cmKaishaId;
    /** SHAIN_SEI: {NotNull, VARCHAR(50)} */
    private String shainSei;
    /** SHAIN_MEI: {NotNull, VARCHAR(50)} */
    private String shainMei;
    /** SHAIN_SEI_EN: {VARCHAR(100)} */
    private String shainSeiEn;
    /** SHAIN_MEI_EN: {VARCHAR(100)} */
    private String shainMeiEn;
    /** LOGIN_CD: {NotNull, VARCHAR(50)} */
    private String loginCd;
    /** PASSWORD: {NotNull, VARCHAR(100)} */
    private String password;

    public void copyFrom(final CmShainForm form) {
        this.setCmShainId(form.getCmShainId());
        this.setCmKaishaId(form.getCmKaishaId());
        this.setShainSei(form.getShainSei());
        this.setShainMei(form.getShainMei());
        this.setShainSeiEn(form.getShainSeiEn());
        this.setShainMeiEn(form.getShainMeiEn());
        this.setLoginCd(form.getLoginCd());
        this.setPassword(form.getPassword());
        this.setVersionNo(form.getVersionNo());
    }
}
