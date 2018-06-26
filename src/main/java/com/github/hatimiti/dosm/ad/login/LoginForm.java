package com.github.hatimiti.dosm.ad.login;

import com.github.hatimiti.dosm.base.Form;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Data
@EqualsAndHashCode
@Component
@Scope(SCOPE_PROTOTYPE)
public class LoginForm implements Form {

    private String loginCd;
    private String password;
}
