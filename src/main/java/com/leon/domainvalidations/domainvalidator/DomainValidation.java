package com.leon.domainvalidations.domainvalidator;


import java.util.Set;

public interface DomainValidation<T> {
    ValidationResult<T> validate(T validationTarget);

    Set<RequestType> applicableRequestTypes();

    boolean shouldExecute(T validationTarget);
}
