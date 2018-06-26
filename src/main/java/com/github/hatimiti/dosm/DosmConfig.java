package com.github.hatimiti.dosm;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DosmSecurityConfig.class)
public class DosmConfig {
}
