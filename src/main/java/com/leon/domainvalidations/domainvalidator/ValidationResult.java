package com.leon.domainvalidations.domainvalidator;

import java.util.function.Predicate;

public class ValidationResult<T> {

    private Status status;
    private String message;
    private T entity;

    public static <T> ValidationResult<T> of(T entity) {
        return new ValidationResult<>(entity);
    }

    private ValidationResult(T entity) {
        this.entity = entity;
    }

    public ValidationResult<T> check(Predicate<T> predicate, String failureReason) {
        if (predicate.test(entity)) {
            this.setStatus(Status.PASSED);
        } else {
            this.setStatus(Status.FAILED);
            this.setMessage(failureReason);
        }
        return this;
    }

    public ValidationResult<T> passed() {
        this.setStatus(Status.PASSED);
        return this;
    }

    public ValidationResult<T> failed(String failureReason) {
        this.setStatus(Status.FAILED);
        this.setMessage(failureReason);
        return this;
    }

    public T getEntity() {
        return entity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
