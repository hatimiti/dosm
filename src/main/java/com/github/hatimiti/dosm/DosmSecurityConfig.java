package com.github.hatimiti.dosm;

import com.github.hatimiti.dosm.ad.login.LoginController;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class DosmSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/*").permitAll()
//                .and()
//                .formLogin()
//                    .loginPage(LoginController.URI)

        ;
    }
}
