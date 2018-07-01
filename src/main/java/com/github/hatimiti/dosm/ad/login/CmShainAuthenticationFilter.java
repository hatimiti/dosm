package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.AccessUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.BeanPropertyBindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmShainAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CmShainAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response) throws AuthenticationException {

        final var loginForm = new LoginForm();
        loginForm.setLoginCd(obtainUsername(request));
        loginForm.setPassword(obtainPassword(request));

        final var errors = new BeanPropertyBindingResult(loginForm, "login");

        if (errors.hasErrors()) {
            throw new AuthenticationException("validate error") {};
        }

        var user = new AccessUser();
        user.setId(loginForm.getLoginCd());
        var token = new UsernamePasswordAuthenticationToken(user, loginForm.getPassword());
        setDetails(request, token);
        return getAuthenticationManager().authenticate(token);
    }

}
