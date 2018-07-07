package com.github.hatimiti.dosm.repository;

import com.github.hatimiti.dosm.repository.entity.CmShain;
import com.github.hatimiti.dosm.repository.entity.Entity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CmShainRepository {

    @Select("SELECT * FROM cm_shain WHERE cm_shain_id = #{id}")
    CmShain selectByPk(@Param("id") Object cmShainId);

    CmShain selectByPkWithRel(@Param("id") Object cmShainId);

    @Select("SELECT * FROM cm_shain WHERE login_cd = #{loginCd} AND password = #{password}")
    CmShain selectByLoginCdAndPassword(@Param("loginCd") String loginCd, @Param("password") String password);

    @Options(useGeneratedKeys = true, keyProperty = "cmShainId")
    @Insert("INSERT INTO cm_shain ("
            + "cm_kaisha_id, shain_sei, shain_mei, shain_sei_en, shain_mei_en, login_cd, password, "
            + Entity.COMMON_COLUMNS + ") VALUES ( "
            + "#{cmKaishaId}, #{shainSei}, #{shainMei}, #{shainSeiEn}, #{shainMeiEn}, #{loginCd}, #{password}, "
            + Entity.COMMON_PLACEHOLDER + ")")
    int save(CmShain cmShain);
}
