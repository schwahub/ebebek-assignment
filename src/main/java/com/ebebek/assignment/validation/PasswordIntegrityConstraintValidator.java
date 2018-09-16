package com.ebebek.assignment.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

public class PasswordIntegrityConstraintValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(7, 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1)
        ));

        RuleResult result = validator.validate(new PasswordData(value));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
               .addConstraintViolation()
               .disableDefaultConstraintViolation();
        return false;

    }

}
