package com.github.hatimiti.dosm.repository;

import com.github.hatimiti.dosm.repository.entity.CmShain;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface CmShainRepository {

    CmShain selectByPkWithRel(@Param("id") Long cmShainId);

    @Select("SELECT * FROM cm_shain WHERE login_cd = #{loginCd} AND password = #{password}")
    CmShain selectByLoginCdAndPassword(@Param("loginCd") String loginCd, @Param("password") String password);

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO cm_shain values ()")
    void save(CmShain cmShain);
}
