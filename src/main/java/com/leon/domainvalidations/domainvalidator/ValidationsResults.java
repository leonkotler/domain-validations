package com.leon.domainvalidations.domainvalidator;

import java.util.LinkedList;
import java.util.List;

public class ValidationsResults {

    private Status status = Status.PASSED;
    private final List<ValidationResult<? extends Validatable>> passedValidations = new LinkedList<>();
    private final List<ValidationResult<? extends Validatable>> failedValidations = new LinkedList<>();
    private final List<ValidationResult<? extends Validatable>> skippedValidations = new LinkedList<>();

    void addValidationResult(ValidationResult<? extends Validatable> validationResult) {
        switch (validationResult.getStatus()) {
            case FAILED:
                failedValidations.add(validationResult);
                status = Status.FAILED;
                break;
            case PASSED:
                passedValidations.add(validationResult);
                break;
            case SKIPPED:
                skippedValidations.add(validationResult);
                break;
        }
    }

    public Status getStatus() {
        return status;
    }

    public List<ValidationResult<? extends Validatable>> getPassedValidations() {
        return passedValidations;
    }

    public List<ValidationResult<? extends Validatable>> getFailedValidations() {
        return failedValidations;
    }

    public List<ValidationResult<? extends Validatable>> getSkippedValidations() {
        return skippedValidations;
    }
}
