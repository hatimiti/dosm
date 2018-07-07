package com.github.hatimiti.dosm.validator;

import com.github.hatimiti.dosm.repository.CmShainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistsCmShainIdValidator
        implements ConstraintValidator<ExistsCmShainId, Object> {

    @Autowired
    private final CmShainRepository cmShainRepository;

    public ExistsCmShainIdValidator(final CmShainRepository cmShainRepository) {
        this.cmShainRepository = cmShainRepository;
    }

    @Override
    public boolean isValid(
            final Object value, final ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }
        return cmShainRepository.selectByPk(value) != null;
    }
}
