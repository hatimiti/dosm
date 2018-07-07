package com.github.hatimiti.dosm.base.thymeleaf;

import com.github.hatimiti.dosm.repository.entity.CmKaisha;

import java.util.List;

public interface DosmDialectHelper {

    CmKaisha cmKaisha(String cmKaishaId);
    List<CmKaisha> cmKaishaList();
}
