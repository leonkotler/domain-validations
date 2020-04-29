package com.leon.domainvalidations.domainvalidator;


import java.util.Set;

public interface Validatable {

    default ValidationsResults validateFor(RequestType requestType) {
        Set<DomainValidation<Validatable>> validations = ValidationFactory.getValidationOf(this.getClass());

        ValidationsResults validationsResults = new ValidationsResults();

        validations
                .stream()
                .filter(validation -> requestType.equals(RequestType.ANY) || validation.eligibleFor().contains(requestType))
                .forEach(validation -> validationsResults.addValidationResult(validation.validate(this)));

        return validationsResults;
    }
}
