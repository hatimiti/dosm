package com.github.hatimiti.dosm.base.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DosmExpressionObjectFactory implements IExpressionObjectFactory {

    private static final String DOSM_COMMON_DIALECT_PREFIX = "d";

    private final DosmDialectHelper dosmDialectHelper;

    @Autowired
    public DosmExpressionObjectFactory(
            final DosmDialectHelper dosmDialectHelper) {
        this.dosmDialectHelper = dosmDialectHelper;
    }

    private static final Set<String> EXPRESSIONS = Collections.unmodifiableSet(
            new HashSet<String>() {{ add(DOSM_COMMON_DIALECT_PREFIX); }});

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return EXPRESSIONS;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        switch (expressionObjectName) {
            case DOSM_COMMON_DIALECT_PREFIX: return dosmDialectHelper;
            default: return dosmDialectHelper;
        }
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return false;
    }
}
