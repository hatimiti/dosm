package com.github.hatimiti.dosm.repository.entity;

import com.github.hatimiti.dosm.ad.master.cmshain.CmShainForm;
import lombok.Data;

@Data
public class CmShain {

    private Long cmShainId;
    private Long cmKaishaId;
    private String shainSei;
    private String shainMei;
    private String shainSeiEn;
    private String shainMeiEn;
    private String loginCd;
    private String password;
    private Integer versionNo;

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
