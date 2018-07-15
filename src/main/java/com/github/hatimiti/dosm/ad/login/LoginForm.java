package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.Form;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm implements Form {

    public static final String LOGIN_CD = "loginCd";
    public static final String PASSWORD = "password";

    @NotBlank
    private String loginCd;
    @NotBlank
    private String password;
}
