package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.ad.master.Mode;
import com.github.hatimiti.dosm.repository.entity.CmShain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;

public class CmShainControllerTest {

    private CmShainController cmShainController;
    private CmShainService cmShainService;

    @BeforeEach
    public void beforeAll() {
        cmShainService = Mockito.mock(CmShainService.class);
        cmShainController = new CmShainController(
                cmShainService,
                Mockito.mock(MessageSource.class));
    }

    @Test
    @DisplayName("初期表示")
    public void testIndex() {
        // given:
        final CmShainListForm cmShainListForm = new CmShainListForm();

        // when:
        final ModelAndView result = cmShainController.index(cmShainListForm);

        // then:
        assertEquals("/ad/master/cmShain/index.html", result.getViewName());
    }

    @Test
    @DisplayName("検索: 正常系")
    public void testSearch() {
        // given:
        final Pageable pageable = Mockito.mock(Pageable.class);
        final CmShainListForm cmShainListForm = new CmShainListForm();
        final BindingResult bind = Mockito.mock(BindingResult.class);

        // when:
        final ModelAndView result = cmShainController.search(
                pageable, cmShainListForm, bind);

        // then:
        assertEquals("/ad/master/cmShain/index.html", result.getViewName());
        assertNotNull(cmShainListForm.pageable);
    }

    @Test
    @DisplayName("検索: 入力エラー")
    public void testSearchInvalidForm() {
        // given:
        final Pageable pageable = Mockito.mock(Pageable.class);
        final CmShainListForm cmShainListForm = new CmShainListForm();
        final BindingResult bind = Mockito.mock(BindingResult.class);
        Mockito.when(bind.hasErrors()).thenReturn(true);

        // when:
        final ModelAndView result= cmShainController.search(
                pageable, cmShainListForm, bind);
        final CmShainListForm resultForm = (CmShainListForm) result.getModelMap().get("form");

        // then:
        assertEquals("/ad/master/cmShain/index.html", result.getViewName());
        assertNull(resultForm.getPageable());
    }

    @Test
    public void testPrepareRegister() {
        // given:
        final CmShainForm cmShainForm = new CmShainForm();

        // when:
        final ModelAndView result = cmShainController.prepareRegister(cmShainForm);
        final CmShainForm resultForm = (CmShainForm) result.getModelMap().get("form");

        // then:
        assertEquals("/ad/master/cmShain/edit.html", result.getViewName());
        assertNull(resultForm.getCmShainId());
        assertEquals(Mode.Register, resultForm.getMode());
    }

    @Test
    public void testConfirmRegister() {
        // given:
        final CmShainForm cmShainForm = new CmShainForm();
        cmShainForm.setMode(Mode.Register);
        cmShainForm.setShainSei("テスト");
        cmShainForm.setShainMei("太郎");
        final BindingResult bind = Mockito.mock(BindingResult.class);

        // when:
        final ModelAndView result = cmShainController
                .confirmRegister(cmShainForm, bind);
        final CmShainForm resultForm = (CmShainForm) result.getModelMap().get("form");

        // then:
        assertEquals("/ad/master/cmShain/confirm.html", result.getViewName());
        assertEquals("テスト", resultForm.getShainSei());
        assertEquals("太郎", resultForm.getShainMei());
        assertEquals(Mode.Register, resultForm.getMode());
    }

    @Test
    public void testConfirmRegisterInvalidForm() {
        // given:
        final CmShainForm cmShainForm = new CmShainForm();
        cmShainForm.setMode(Mode.Register);
        cmShainForm.setShainSei("テスト");
        cmShainForm.setShainMei("太郎");
        final BindingResult bind = Mockito.mock(BindingResult.class);
        Mockito.when(bind.hasErrors()).thenReturn(true);

        // when:
        final ModelAndView result = cmShainController
                .confirmRegister(cmShainForm, bind);
        final CmShainForm resultForm = (CmShainForm) result.getModelMap().get("form");

        // then:
        assertEquals("/ad/master/cmShain/edit.html", result.getViewName());
        assertEquals("テスト", resultForm.getShainSei());
        assertEquals("太郎", resultForm.getShainMei());
        assertEquals(Mode.Register, resultForm.getMode());
    }

    @Test
    public void testRegister() {
        // given:
        final CmShainForm cmShainForm = new CmShainForm();
        final BindingResult bind = Mockito.mock(BindingResult.class);
        final RedirectAttributes ra = Mockito.mock(RedirectAttributes.class);

        CmShain cmShain = new CmShain();
        cmShain.setCmShainId(1L);
        Mockito.when(cmShainService.register(cmShainForm)).thenReturn(cmShain);

        // when:
        final ModelAndView result = cmShainController.register(
                cmShainForm, bind, ra);
        final CmShainForm resultForm = (CmShainForm) result.getModelMap().get("form");

        // then:
        assertEquals("redirect:complete", result.getViewName());
    }
}
