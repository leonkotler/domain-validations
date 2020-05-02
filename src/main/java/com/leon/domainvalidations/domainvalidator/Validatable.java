package com.leon.domainvalidations.domainvalidator;


import java.util.Set;

public interface Validatable {

    default ValidationsResults validate() {
        return validateFor(RequestType.ANY);
    }

    default ValidationsResults validateFor(RequestType requestType) {
        Set<DomainValidation<Validatable>> validations = ValidationFactory.getValidationsOf(this.getClass());

        ValidationsResults validationsResults = new ValidationsResults();

        validations
                .stream()
                .filter(validation -> validation.shouldExecute(this))
                .filter(validation ->
                        requestType.equals(RequestType.ANY) ||
                                validation.applicableRequestTypes().contains(RequestType.ANY) ||
                                validation.applicableRequestTypes().contains(requestType))
                .forEach(validation -> validationsResults.addValidationResult(validation.validate(this)));

        return validationsResults;
    }
}
