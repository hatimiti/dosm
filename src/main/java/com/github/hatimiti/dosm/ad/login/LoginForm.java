package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.Form;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Data
@EqualsAndHashCode
@Component
//@Scope(SCOPE_PROTOTYPE)
@Validated
public class LoginForm implements Form {

    @NotEmpty
    private String loginCd;
    @NotEmpty
    private String password;
}
