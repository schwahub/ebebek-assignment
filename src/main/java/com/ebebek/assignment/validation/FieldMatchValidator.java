package com.ebebek.assignment.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
            final Object firstObj = wrapper.getPropertyValue(firstFieldName);
            final Object secondObj = wrapper.getPropertyValue(secondFieldName);
            valid = firstObj.equals(secondObj);
        } catch (final Exception ignore) {
        }
        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                   .addPropertyNode(firstFieldName)
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
        }
        return valid;
    }
}