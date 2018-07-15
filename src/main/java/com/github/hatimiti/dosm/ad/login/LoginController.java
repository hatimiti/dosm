package com.github.hatimiti.dosm.ad.login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.github.hatimiti.dosm.base.DosmModelAndView.forward;
import static com.github.hatimiti.dosm.base.DosmModelAndView.view;
import static org.springframework.beans.BeanUtils.copyProperties;

@Controller
@RequestMapping(LoginController.URI)
public class LoginController {

    public static final String URI = "/ad/login";

    @GetMapping
    public ModelAndView index(final LoginForm form) {
        copyProperties(new LoginForm(), form);
        return view(URI, "/login.html", form);
    }

    @PostMapping(params = "login")
    public ModelAndView login(
            final @Validated LoginForm form,
            final BindingResult bind) {

        if (bind.hasErrors()) {
            return view(URI, "/login.html", form, bind);
        }
        return forward(CmShainAuthenticationFilter.URI);
    }

}
