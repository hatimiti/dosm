package com.github.hatimiti.dosm.base.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

@Component
public class DosmExpressionObjectDialect implements IExpressionObjectDialect {

    private final DosmExpressionObjectFactory dosmExpressionObjectFactory;

    @Autowired
    public DosmExpressionObjectDialect(
            final DosmExpressionObjectFactory dosmExpressionObjectFactory) {
        this.dosmExpressionObjectFactory = dosmExpressionObjectFactory;
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return dosmExpressionObjectFactory;
    }

    @Override
    public String getName() {
        return DosmExpressionObjectDialect.class.getSimpleName();
    }
}
