package com.github.hatimiti.dosm.base.form;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PageParam {
    boolean escape() default true;
}
