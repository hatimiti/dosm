package com.github.hatimiti.dosm;

import com.github.hatimiti.dosm.ad.login.CmShainAuthenticationFilter;
import com.github.hatimiti.dosm.ad.login.CmShainAuthenticationProvider;
import com.github.hatimiti.dosm.ad.login.LoginController;
import com.github.hatimiti.dosm.ad.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class DosmSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    CmShainAuthenticationProvider cmShainAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", dosm(IndexController.URI));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Ref: https://fueiseix.hatenablog.com/entry/2018/03/11/191200
        // 全体設定
        http.authorizeRequests()
            .mvcMatchers(LoginController.URI).permitAll()
            .anyRequest().authenticated()
        ;
        // ログイン設定
        http.formLogin()
            .loginPage(LoginController.URI)
            .loginProcessingUrl(LoginController.LOGIN_URI)
        ;
        // ログアウト設定
        http.logout()
            .logoutSuccessUrl(LoginController.URI)
        ;

        http.addFilterBefore(
                cmShainAuthenticationFilter(), CmShainAuthenticationFilter.class);

    }

    public Filter cmShainAuthenticationFilter() throws Exception {
        final var filter = new CmShainAuthenticationFilter();
        filter.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher(LoginController.LOGIN_URI, HttpMethod.POST.name()));
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setUsernameParameter("loginCd");
        filter.setPasswordParameter("password");
        filter.setAuthenticationSuccessHandler(
                new SimpleUrlAuthenticationSuccessHandler(MenuController.URI));
        filter.setAuthenticationFailureHandler(
                new SimpleUrlAuthenticationFailureHandler(LoginController.URI));
        return filter;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(cmShainAuthenticationProvider);
    }

}
