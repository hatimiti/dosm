package com.github.hatimiti.dosm.repository.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class Entity implements Serializable {

    public static final String COMMON_COLUMNS = " reg_user_id, reg_tm, reg_func_cd, upd_user_id, upd_tm, upd_func_cd, version_no ";
    public static final String COMMON_PLACEHOLDER = " #{regUserId}, #{regTm}, #{regFuncCd}, #{updUserId}, #{updTm}, #{updFuncCd}, #{versionNo} ";

    /** REG_USER_ID: {NotNull, VARCHAR(10)} */
    protected String regUserId;

    /** REG_TM: {NotNull, TIMESTAMP(23, 10)} */
    protected java.time.LocalDateTime regTm;

    /** REG_FUNC_CD: {NotNull, VARCHAR(9)} */
    protected String regFuncCd;

    /** UPD_USER_ID: {NotNull, VARCHAR(10)} */
    protected String updUserId;

    /** UPD_TM: {NotNull, TIMESTAMP(23, 10)} */
    protected java.time.LocalDateTime updTm;

    /** UPD_FUNC_CD: {NotNull, VARCHAR(9)} */
    protected String updFuncCd;

    /** VERSION_NO: {NotNull, DECIMAL(9)} */
    protected Integer versionNo;

    public void setupCommonColumnOfInsert() {
        final String regUserId = /*TODO*/"ADMIN";
        setRegUserId(regUserId);
        final java.time.LocalDateTime regTm = /*TODO*/LocalDateTime.now();
        setRegTm(regTm);
        final String regFuncCd = /*TODO*/"FXXX";
        setRegFuncCd(regFuncCd);
        final String updUserId = getRegUserId();
        setUpdUserId(updUserId);
        final java.time.LocalDateTime updTm = getRegTm();
        setUpdTm(updTm);
        final String updFuncCd = getRegFuncCd();
        setUpdFuncCd(updFuncCd);
        final Integer versionNo = 1;
        setVersionNo(versionNo);
    }
}
