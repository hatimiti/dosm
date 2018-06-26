package com.github.hatimiti.dosm.repository;

import com.github.hatimiti.dosm.repository.entity.CmShain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface CmShainRepository {
    @Select("SELECT * FROM cm_shain WHERE login_cd = #{loginCd} AND password = #{password}")
    CmShain selectByLoginCdAndPassword(@Param("loginCd") String loginCd, @Param("password") String password);
}
