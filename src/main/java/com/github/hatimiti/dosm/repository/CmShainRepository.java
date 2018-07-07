package com.github.hatimiti.dosm.repository;

import com.github.hatimiti.dosm.repository.entity.CmShain;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.OptimisticLockingFailureException;

import static com.github.hatimiti.dosm.repository.entity.Entity.*;

@Mapper
public interface CmShainRepository {

    @Select("SELECT * FROM cm_shain WHERE cm_shain_id = #{id}")
    CmShain selectByPk(@Param("id") Object cmShainId);

    @Select("SELECT * FROM cm_shain WHERE cm_shain_id = #{id} FOR UPDATE")
    CmShain selectByPk4Update(@Param("id") Object cmShainId);

    CmShain selectByPkWithRel(@Param("id") Object cmShainId);

    @Select("SELECT * FROM cm_shain WHERE login_cd = #{loginCd} AND password = #{password}")
    CmShain selectByLoginCdAndPassword(@Param("loginCd") String loginCd, @Param("password") String password);

    @Options(useGeneratedKeys = true, keyProperty = "cmShainId")
    @Insert("INSERT INTO cm_shain ("
            + "cm_kaisha_id, shain_sei, shain_mei, shain_sei_en, shain_mei_en, login_cd, password, "
            + COMMON_COLUMNS + ") VALUES ( "
            + "#{cmKaishaId}, #{shainSei}, #{shainMei}, #{shainSeiEn}, #{shainMeiEn}, #{loginCd}, #{password}, "
            + COMMON_PLACEHOLDER + ")")
    int insert(CmShain cmShain);

    @Update("UPDATE cm_shain SET "
            + "cm_kaisha_id = #{cmKaishaId}, shain_sei = #{shainSei}, shain_mei = #{shainMei}, "
            + "shain_sei_en = #{shainSeiEn}, shain_mei_en = #{shainMeiEn}, "
            + "login_cd = #{loginCd}, password = #{password}, "
            + COMMON_COLUMNS_FOR_UPDATE
            + "WHERE cm_shain_id = #{cmShainId} AND " + WHERE_VERSION_NO)
    int update(CmShain cmShain);

    default int updateOptimistic(CmShain cmShain) {
        int count = update(cmShain);
        if (count != 1) {
            throw new OptimisticLockingFailureException(
                    String.format("cmShainId: %s, versionNo: %s, count: %s",
                            cmShain.getCmShainId(), cmShain.getVersionNo(), count));
        }
        return count;
    }

    @Delete("DELETE FROM cm_shain WHERE cm_shain_id = #{cmShainId} AND " + WHERE_VERSION_NO)
    int delete(CmShain cmShain);
    default int deleteOptimistic(CmShain cmShain) {
        int count = delete(cmShain);
        if (count != 1) {
            throw new OptimisticLockingFailureException(
                    String.format("cmShainId: %s, versionNo: %s, count: %s",
                            cmShain.getCmShainId(), cmShain.getVersionNo(), count));
        }
        return count;
    }
}
