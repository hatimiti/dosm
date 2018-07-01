package com.github.hatimiti.dosm.ad.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.github.hatimiti.dosm.base.DosmModelAndView.view;
import static org.springframework.beans.BeanUtils.copyProperties;

@Controller
@RequestMapping(LoginController.URI)
public class LoginController {

    public static final String URI = "/ad/login";
    public static final String LOGIN_URI = URI + "/do";

    @RequestMapping
    public ModelAndView index(final LoginForm form) {
        copyProperties(new LoginForm(), form);
        return view(URI, "/login.html", form);
    }

}
