package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.ad.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.github.hatimiti.dosm.base.DosmModelAndView.redirect;
import static com.github.hatimiti.dosm.base.DosmModelAndView.view;
import static org.springframework.beans.BeanUtils.copyProperties;

@Controller
@SessionAttributes(types = LoginForm.class)
@RequestMapping(LoginController.URI)
public class LoginController {

    public static final String URI = "/ad/login/";

    private final LoginService loginService;

    @Autowired
    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping
    public ModelAndView index(final LoginForm form) {
        copyProperties(new LoginForm(), form);
        return view(URI, "login.html", form);
    }

    @RequestMapping(params = "login")
    public ModelAndView login(final LoginForm form, final RedirectAttributes ra) {
        this.loginService.login(form);
//        renewSession4FixationAttack(request);

        return redirect(MenuController.URI, ra);
    }
}
