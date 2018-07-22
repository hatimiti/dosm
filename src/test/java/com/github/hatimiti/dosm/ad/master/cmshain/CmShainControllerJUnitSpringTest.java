package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.DosmApplication;
import com.github.hatimiti.dosm.ad.master.Mode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
//-> @org.springframework.test.context.junit.jupiter.SpringJUnitConfig
@WebAppConfiguration
//-> @org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
public class CmShainControllerJUnitSpringTest {

    private static final String XPATH_TITLE
            = "/html/head/title";
    private static final String XPATH_FIRST_SEARCH_RESULT
            = "//*[@id=\"SAMPLE-CONTENTS\"]/div/table/tbody[1]/tr/td[1]";
    private static final String XPATH_VALID_CM_SHAIN_ID
            = "//*[@id=\"SAMPLE-CONTENTS\"]/form[1]/div";
    private static final String XPATH_EDIT_CM_SHAIN_ID
            = "//*[@id=\"SAMPLE-CONTENTS\"]/form/span[1]/span";
    private static final String XPATH_EDIT_VALID_SHAIN_MEI
            = "//*[@id=\"SAMPLE-CONTENTS\"]/form/div[1]";

    private MockMvc mvc;

    @BeforeEach
    public void beforeEach(WebApplicationContext wac) {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @DisplayName("初期表示")
    public void testIndex() throws Exception {
        mvc.perform(get("/ad/master/cmShain/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/ad/master/cmShain/index.html"))
        ;
    }

    @Test
    @DisplayName("検索: 正常系")
    public void testSearch() throws Exception {
        mvc.perform(get("/ad/master/cmShain/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("/ad/master/cmShain/index.html"))
                // xpath: https://msdn.microsoft.com/ja-jp/library/ms256086(v=vs.120).aspx
                .andExpect(xpath(XPATH_TITLE).string("社員一覧画面"))
                .andExpect(xpath(XPATH_FIRST_SEARCH_RESULT).string("1"))
        ;
    }

    @Test
    @DisplayName("検索: 入力エラー")
    public void testSearchInvalidForm() throws Exception {
        mvc.perform(get("/ad/master/cmShain/search")
                    .param("cmShainId", "abc"))
                .andExpect(status().isOk())
                .andExpect(view().name("/ad/master/cmShain/index.html"))
                .andExpect(xpath(XPATH_TITLE).string("社員一覧画面"))
                .andExpect(xpath(XPATH_VALID_CM_SHAIN_ID)
                        .string("Failed to convert property value of type java.lang.String to required type java.lang.Long for property cmShainId; nested exception is java.lang.NumberFormatException: For input string: \"abc\""))
                .andExpect(xpath(XPATH_FIRST_SEARCH_RESULT).string(""))
        ;
    }

    @Test
    public void testPrepareRegister() throws Exception {
        mvc.perform(post("/ad/master/cmShain")
                    .param("prepareRegister", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("/ad/master/cmShain/edit.html"))
                .andExpect(xpath(XPATH_TITLE).string("社員登録画面"))
                .andExpect(xpath(XPATH_EDIT_CM_SHAIN_ID).string(""))
        ;
    }

    @Test
    public void testConfirmRegister() throws Exception {
        // Controller メソッドの引数を自前で渡したい場合は flashAttr に設定する。
        final CmShainForm cmShainForm = new CmShainForm();
        cmShainForm.setMode(Mode.Register);
        mvc.perform(post("/ad/master/cmShain")
                        .flashAttr("cmShainForm", cmShainForm)
                        .param("confirmRegister", "")
                        .param("cmKaishaId", "1")
                        .param("shainSei", "テスト")
                        .param("shainMei", "太郎")
                        .param("loginCd", "abc")
                        .param("password", "abc")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("/ad/master/cmShain/confirm.html"))
                .andExpect(xpath(XPATH_TITLE).string("社員登録確認画面"))
                .andExpect(xpath(XPATH_EDIT_CM_SHAIN_ID).string(""))
        ;
    }

    @Test
    public void testConfirmRegisterInvalidForm() throws Exception {
        final CmShainForm cmShainForm = new CmShainForm();
        cmShainForm.setMode(Mode.Register);
        mvc.perform(post("/ad/master/cmShain")
                .flashAttr("cmShainForm", cmShainForm)
                .param("confirmRegister", "")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("/ad/master/cmShain/edit.html"))
                .andExpect(xpath(XPATH_TITLE).string("社員登録画面"))
                .andExpect(xpath(XPATH_EDIT_VALID_SHAIN_MEI).string("\"社員名(姓)\"は必須です。"))
        ;
    }

    @Test
    public void testRegister() throws Exception {
        final CmShainForm cmShainForm = new CmShainForm();
        cmShainForm.setMode(Mode.Register);
        mvc.perform(post("/ad/master/cmShain")
                .flashAttr("cmShainForm", cmShainForm)
                .param("register", "")
                .param("cmKaishaId", "1")
                .param("shainSei", "テスト")
                .param("shainMei", "太郎")
                .param("loginCd", "abc")
                .param("password", "abc")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:complete"))
                .andExpect(redirectedUrl("complete"))
        ;
    }

    @Configuration
    @Import(DosmApplication.class)
    static class TestConfig {

    }
}
