package com.leon.domainvalidations.user.validations;

import com.leon.domainvalidations.user.User;
import com.leon.domainvalidations.domainvalidator.DomainValidation;
import com.leon.domainvalidations.domainvalidator.RequestType;
import com.leon.domainvalidations.domainvalidator.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserNameValidation implements DomainValidation<User> {

    @Override
    public ValidationResult<User> validate(User target) {
        return ValidationResult
                .of(target)
                .check(e -> e.getName().equals("Leon"), "Wrong Name!");
    }

    @Override
    public Set<RequestType> eligibleFor() {
        return Set.of(RequestType.ANY);
    }
}
