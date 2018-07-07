package com.github.hatimiti.dosm.base.thymeleaf;

import com.github.hatimiti.dosm.repository.CmKaishaRepository;
import com.github.hatimiti.dosm.repository.entity.CmKaisha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DosmDialectHelperImpl implements DosmDialectHelper {

    private final CmKaishaRepository cmKaishaRepository;

    @Autowired
    public DosmDialectHelperImpl(
            final CmKaishaRepository cmKaishaRepository) {
        this.cmKaishaRepository = cmKaishaRepository;
    }

    @Override
    public CmKaisha cmKaisha(final String cmKaishaId) {
        return Optional.ofNullable(cmKaishaRepository
                .selectByPk(cmKaishaId)).orElseGet(CmKaisha::new);
    }

    @Override
    public List<CmKaisha> cmKaishaList() {
        return cmKaishaRepository.selectAll();
    }
}
