package com.leon.domainvalidations.domainvalidator;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationsResults {

    private Status status = Status.PASSED;

    private final List<ValidationResult<? extends Validatable>> passedValidations = new LinkedList<>();
    private final List<ValidationResult<? extends Validatable>> failedValidations = new LinkedList<>();

    void addValidationResult(ValidationResult<? extends Validatable> validationResult) {
        switch (validationResult.getStatus()) {
            case FAILED:
                failedValidations.add(validationResult);
                status = Status.FAILED;
                break;
            case PASSED:
                passedValidations.add(validationResult);
                break;
        }
    }

    public Status getStatus() {
        return status;
    }

    public boolean isFailed() {
        return status.equals(Status.FAILED);
    }

    public boolean isPassed() {
        return status.equals(Status.PASSED);
    }

    public List<ValidationResult<? extends Validatable>> getPassedValidations() {
        return passedValidations;
    }

    public List<ValidationResult<? extends Validatable>> getFailedValidations() {
        return failedValidations;
    }

    public List<String> getFailureMessages() {
        return failedValidations.stream().map(ValidationResult::getMessage).collect(Collectors.toList());
    }

}
