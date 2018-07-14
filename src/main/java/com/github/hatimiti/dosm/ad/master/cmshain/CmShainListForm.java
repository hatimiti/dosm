package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.base.Form;
import com.github.hatimiti.dosm.base.form.PageParam;
import com.github.hatimiti.dosm.base.form.Pager;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import com.github.hatimiti.dosm.validator.ExistsCmShainId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Size;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class CmShainListForm implements Form /*extends BaseSortPageForm*/ {

    @ExistsCmShainId
    @PageParam @Getter @Setter private Long cmShainId;

    @PageParam @Getter @Setter private Long cmKaishaId;

    @Size(max = 50)
    @PageParam @Getter @Setter private String shainMei;

    @PageParam @Getter @Setter private boolean compresses;

    @Getter Pageable pageable;
    @Getter Pager<CmShain> shainList;

    public String getShainMeiLike() {
        return escapeForLIKE(shainMei, '¥');
    }

    public static String escapeForLIKE(final String value, final char esc) {
        if (value == null && value.isEmpty()) {
            return "";
        }
        return value.codePoints()
                .mapToObj(c -> {
                    return String.valueOf((char) c)
                            .replaceAll("%", esc + "%")
                            .replaceAll("％", esc + "％")
                            .replaceAll("_", esc + "_")
                            .replaceAll("＿", esc + "＿")
                            .replaceAll("'", esc + "'")
                            .replaceAll("’", esc + "’")
                    ;
                })
                .collect(Collectors.joining());
    }
}
