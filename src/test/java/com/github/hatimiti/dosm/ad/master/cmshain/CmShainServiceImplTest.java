package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.DosmApplication;
import com.github.hatimiti.dosm.repository.CmShainRepository;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class CmShainServiceImplTest {

    @Autowired
    private CmShainServiceImpl cmShainService;

    @BeforeEach
    public void beforeEach() {
    }

    @Test
    @DisplayName("初期表示")
    public void testIndex() {
        // given:

        // when:
        final CmShain cmShain = cmShainService.selectByPkWithRel(1L);

        // then:
        Assertions.assertEquals(Long.valueOf(1), cmShain.getCmShainId());
    }

    @Configuration
    @Import(DosmApplication.class)
    static class TestConfig {
    }

}
