package com.leon.domainvalidations.domainvalidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ValidationFactory {

    protected static Map<Class<?>, Set<DomainValidation<Validatable>>> validations = new HashMap<>();

    public static Set<DomainValidation<Validatable>> getValidationsOf(Class<?> clazz) {
        return validations.get(clazz);
    }
}
