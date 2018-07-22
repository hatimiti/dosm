package com.github.hatimiti.dosm.repository;

import com.github.hatimiti.dosm.ad.master.cmshain.CmShainListForm;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import lombok.val;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

import static com.github.hatimiti.dosm.repository.entity.Entity.*;

@Mapper
public interface CmShainRepository {

    /** @deprecated Calls for internal only */
    @Deprecated long _countPageForMaster(CmShainListForm form);

    /** @deprecated Calls for internal only */
    @Deprecated List<CmShain> _selectPageForMaster(CmShainListForm form);

    default Page<CmShain> selectPageForMaster(final CmShainListForm form) {

        val count = _countPageForMaster(form);
        List<CmShain> shainList = Collections.<CmShain>emptyList();
        if (0 < count) {
            shainList = _selectPageForMaster(form);
        }
        return new PageImpl<>(shainList, form.getPageable(), count);
    }

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

    /** @deprecated Calls for internal only */
    @Deprecated
    @Update("UPDATE cm_shain SET "
            + "cm_kaisha_id = #{cmKaishaId}, shain_sei = #{shainSei}, shain_mei = #{shainMei}, "
            + "shain_sei_en = #{shainSeiEn}, shain_mei_en = #{shainMeiEn}, "
            + "login_cd = #{loginCd}, password = #{password}, "
            + COMMON_COLUMNS_FOR_UPDATE
            + "WHERE cm_shain_id = #{cmShainId} AND " + WHERE_VERSION_NO)
    int _update(CmShain cmShain);

    default int update(final CmShain cmShain) {
        val count = _update(cmShain);
        if (count != 1) {
            throw new OptimisticLockingFailureException(
                    String.format("cmShainId: %s, versionNo: %s, count: %s",
                            cmShain.getCmShainId(), cmShain.getVersionNo(), count));
        }
        return count;
    }

    /** @deprecated Calls for internal only */
    @Deprecated
    @Delete("DELETE FROM cm_shain WHERE cm_shain_id = #{cmShainId} AND " + WHERE_VERSION_NO)
    int _delete(CmShain cmShain);

    default int delete(final CmShain cmShain) {
        val count = _delete(cmShain);
        if (count != 1) {
            throw new OptimisticLockingFailureException(
                    String.format("cmShainId: %s, versionNo: %s, count: %s",
                            cmShain.getCmShainId(), cmShain.getVersionNo(), count));
        }
        return count;
    }
}
