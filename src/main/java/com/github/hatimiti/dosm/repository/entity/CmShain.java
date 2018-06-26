package com.github.hatimiti.dosm.repository.entity;

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

}
