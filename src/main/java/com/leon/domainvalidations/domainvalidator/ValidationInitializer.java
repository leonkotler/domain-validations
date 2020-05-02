package com.leon.domainvalidations.domainvalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;

@Component
public class ValidationInitializer {

    private final List<DomainValidation<? extends Validatable>> allValidations;

    @Autowired
    public ValidationInitializer(List<DomainValidation<? extends Validatable>> allValidations) {
        this.allValidations = allValidations;
    }

    @PostConstruct
    void initializeValidationsFactory() {
        allValidations.forEach(validation -> {

            Class<?> classOfValidatedEntity = getClassOfValidatedEntity(validation);
            ValidationFactory.validations.putIfAbsent(classOfValidatedEntity, new HashSet<>());
            //noinspection unchecked
            ValidationFactory.validations.get(classOfValidatedEntity).add((DomainValidation<Validatable>) validation);
            /*  The downcast is okay because:
                1. Spring will only inject the correct correct beans of DomainValidation<? extends Validatable>
                2. Validatable is the actual interface that we want to put as the generic type of DomainValidation,
                   so this upper bound is just fine.
             */
        });
    }

    private Class<?> getClassOfValidatedEntity(DomainValidation<? extends Validatable> validation) {
        Type entityToValidateType = ((ParameterizedType) validation.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        try {
            return Class.forName(entityToValidateType.getTypeName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't retrieve the class of " + entityToValidateType.getTypeName(), e);
        }
    }


}
