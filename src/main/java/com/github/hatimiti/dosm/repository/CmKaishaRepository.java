package com.github.hatimiti.dosm.repository;

import com.github.hatimiti.dosm.repository.entity.CmKaisha;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CmKaishaRepository {

    @Select("SELECT * FROM cm_kaisha WHERE cm_kaisha_id = #{id}")
    CmKaisha selectByPk(@Param("id") Object cmKaishaId);

    @Select("SELECT * FROM cm_kaisha")
    List<CmKaisha> selectAll();
}
