package com.github.hatimiti.dosm.base.thymeleaf;

import com.github.hatimiti.dosm.repository.entity.CmKaisha;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Primary
@Component
public class TestDialectHelperImpl implements DosmDialectHelper {
    @Override
    public CmKaisha cmKaisha(String cmKaishaId) {
        return new CmKaisha();
    }

    @Override
    public List<CmKaisha> cmKaishaList() {
        return Collections.emptyList();
    }
}
