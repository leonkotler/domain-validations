package com.leon.domainvalidations.domainvalidator;


import java.util.Set;

public interface DomainValidation<T> {
    ValidationResult<T> validate(T target);

    Set<RequestType> eligibleFor();
}
