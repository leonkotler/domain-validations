package com.leon.domainvalidations.user.validations;


import com.leon.domainvalidations.user.User;
import com.leon.domainvalidations.domainvalidator.DomainValidation;
import com.leon.domainvalidations.domainvalidator.RequestType;
import com.leon.domainvalidations.domainvalidator.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class UserAgeValidation implements DomainValidation<User> {

    @Override
    public ValidationResult<User> validate(User validationTarget) {
        if (validationTarget.getAge() > 18) {
            return ValidationResult.of(validationTarget).passed();
        } else {
            return ValidationResult.of(validationTarget).failed("Age must be greater than 18!");
        }
    }

    @Override
    public Set<RequestType> applicableRequestTypes() {
        return Set.of(RequestType.CREATE, RequestType.UPDATE);
    }

    @Override
    public boolean shouldExecute(User validationTarget) {
        return true;
    }
}
